package pairmatching.controller;

import pairmatching.domain.*;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

// 메뉴 입력 처리 - Map 이용
public class PairController {
    private static final String PAIR_MENU_OPTION = "1";
    private static final String SEARCH_MENU_OPTION = "2";
    private static final String INITIALIZE_MENU_OPTION = "3";
    private static final String QUIT_MENU_OPTION = "Q";
    private static final String INVALID_INPUT_MESSAGE = "[ERROR] 잘못된 입력입니다.";
    private static final String BACKEND_FILE_PATH = "src/main/resources/backend-crew.md";
    private static final String FRONTEND_FILE_PATH = "src/main/resources/frontend-crew.md";
    private static final String INPUT_NO = "아니오";
    private static final String INPUT_YES = "네";

    private final CrewRepository crewRepository = new CrewRepository();
    private final PairMatcher pairMatcher = new PairMatcher(crewRepository);
    private final Map<String, Runnable> menuOptions = new HashMap<>();

    public PairController() {
        menuOptions.put(PAIR_MENU_OPTION, this::handlePairMenuOption);
        menuOptions.put(SEARCH_MENU_OPTION, this::handleSearchMenuOption);
        menuOptions.put(INITIALIZE_MENU_OPTION, this::handleInitializeMenuOption);
        menuOptions.put(QUIT_MENU_OPTION, () -> {});
    }

    public void run() {
        retryUntilSuccess(() -> {
            crewRepository.loadCrewsFromFiles(BACKEND_FILE_PATH, FRONTEND_FILE_PATH);
            pairMatchLoop();
            return null;
        });
    }

    private void pairMatchLoop() {
        while (true) {
            String menuInput = InputView.selectMenuOption();
            Runnable action = menuOptions.get(menuInput);
            if (action != null) {
                action.run();
                if (menuInput.equals(QUIT_MENU_OPTION)) break;
            } else {
                System.out.println(INVALID_INPUT_MESSAGE);
            }
        }
    }

    private void handlePairMenuOption() {
        retryUntilSuccess(() -> {
            matchPair();
            return null;
        });
    }

    private void handleSearchMenuOption() {
        retryUntilSuccess(() -> {
            searchPair();
            return null;
        });
    }

    private void handleInitializeMenuOption() {
        retryUntilSuccess(() -> {
            initializePair();
            return null;
        });
    }

    private void initializePair() {
        pairMatcher.initializeMatchingResults();
    }

    private void searchPair() {
        CourseLevelMissionInput selectedCourseLevelMission = readAndParseCourseLevelMission();
        MatchingInfo matchingResult = getMatchingInfo(selectedCourseLevelMission);
        OutputView.outputPairMatching(matchingResult.toString());
    }

    private void matchPair() {
        CourseLevelMissionInput selectedCourseLevelMission = readAndParseCourseLevelMission();
        retryUntilSuccess(() -> {
            if (checkAndHandleRematching(selectedCourseLevelMission)) {
                performPairMatching(selectedCourseLevelMission);
            }
            return null;
        });
    }

    private boolean checkAndHandleRematching(CourseLevelMissionInput selectedCourseLevelMission) {
        if (pairMatcher.hasPreviousMatchingInfo(selectedCourseLevelMission)) {
            String rematchingInput = InputView.readRematching();
            if (rematchingInput.equals(INPUT_NO)) {
                return false;
            }
            if (!rematchingInput.equals(INPUT_YES)) {
                throw new IllegalArgumentException(INVALID_INPUT_MESSAGE);
            }
        }
        return true;
    }

    private void performPairMatching(CourseLevelMissionInput selectedCourseLevelMission) {
        pairMatcher.matchPairs(selectedCourseLevelMission);
        MatchingInfo matchingResult = getMatchingInfo(selectedCourseLevelMission);
        OutputView.outputPairMatching(matchingResult.toString());
    }

    private CourseLevelMissionInput readAndParseCourseLevelMission() {
        String courseLevelMission = InputView.readCourseLevelMission();
        return InputParser.parseCourseLevelMission(courseLevelMission);
    }

    private MatchingInfo getMatchingInfo(CourseLevelMissionInput selectedCourseLevelMission) {
        return pairMatcher.findMatchingInfo(selectedCourseLevelMission);
    }

    static <T> T retryUntilSuccess(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
