package pairmatching.controller;

import java.util.List;
import java.util.function.Supplier;
import pairmatching.controller.dto.Stage;
import pairmatching.domain.constants.GameCommand;
import pairmatching.domain.constants.Level;
import pairmatching.domain.constants.Mission;
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
        }
    }

    private void match() {
        outputView.printCourseAndMission();
        Stage stage = retry(() -> {
            Stage _stage = inputView.readStage();
            if (doesNotContains(_stage.level(), _stage.mission())) {
                throw CustomException.from(ErrorMessage.NOT_MATCHED_MISSION);
            }
            return _stage;
        });
        pairService.execute(stage);
    }

    private boolean doesNotContains(Level level, Mission mission) {
        return level.getMissions().contains(mission);
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
