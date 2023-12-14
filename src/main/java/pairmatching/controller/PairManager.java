package pairmatching.controller;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import pairmatching.controller.dto.Stage;
import pairmatching.domain.Matching;
import pairmatching.domain.constants.GameCommand;
import pairmatching.domain.constants.Level;
import pairmatching.domain.constants.Mission;
import pairmatching.domain.constants.RematchCommand;
import pairmatching.global.exception.CustomException;
import pairmatching.global.exception.ErrorMessage;
import pairmatching.global.util.CustomFileReader;
import pairmatching.service.PairService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;
import pairmatching.view.console.ConsoleWriter;

public class PairManager {
    private final InputView inputView;
    private final OutputView outputView;
    private final PairService pairService;

    public PairManager(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;

        List<String> backend = CustomFileReader.readFile("resources/backend-crew.md");
        List<String> frontend = CustomFileReader.readFile("resources/frontend-crew.md");
        this.pairService = new PairService(backend, frontend);
    }

    public void run() {
        while (true) {
            GameCommand gameCommand = inputView.readFunction();
            if (gameCommand.equals(GameCommand.QUIT)) {
                break;
            }
            if (gameCommand.equals(GameCommand.MATCHING)) {
                match();
            }
            if (gameCommand.equals(GameCommand.INQUIRE)) {
                inquire();
            }
            if (gameCommand.equals(GameCommand.RESET)) {
                reset();
            }
        }
    }

    /**
     * 페어를 매칭하는 기능
     * <p>
     * 매칭 정보가 이미 있는 경우, 사용자가 종료를 입력하면 바로 종료한다.
     */
    private void match() {
        boolean isRunning = true;
        while (isRunning) {
            outputView.printCourseAndMission();
            Stage stage = retry(() -> {
                return readStage();
            });
            isRunning = tryMatch(stage);
        }
    }

    private boolean tryMatch(Stage stage) {
        for (int i = 0; i < 3; i++) {
            if (pairService.hasMatching(stage) && isQuit()) {
                return true;
            }
            Optional<Matching> matching = pairService.save(stage);
            if (matching.isPresent()) {
                outputView.printMatching(matching.get());
                return false;
            }
        }
        outputView.printOverMatching();
        return true;
    }

    private boolean isQuit() {
        RematchCommand rematchCommand = inputView.readRematchingOrQuit();
        if (rematchCommand.equals(RematchCommand.QUIT)) {
            return true;
        }
        return false;
    }

    private Stage readStage() {
        Stage stage = inputView.readStage();
        if (doesNotContains(stage.level(), stage.mission())) {
            throw CustomException.from(ErrorMessage.NOT_MATCHED_MISSION);
        }
        return stage;
    }

    private boolean doesNotContains(Level level, Mission mission) {
        return level.getMissions().contains(mission);
    }

    /**
     * 페어를 조회하는 기능
     */
    public void inquire() {
        boolean isRunning = true;
        while (isRunning) {
            outputView.printCourseAndMission();
            Stage stage = retry(() -> {
                return readStage();
            });
            Optional<Matching> matching = pairService.getMatching(stage);
            if (matching.isPresent()) {
                outputView.printMatching(matching.get());
            }
            outputView.printNoMatching();
        }
    }

    /**
     * 페어를 초기화하는 기능
     */
    public void reset() {
        pairService.deleteAll();
    }

    private static <T> T retry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                ConsoleWriter.printlnMessage(e.getMessage());
            }
        }
    }
}
