package pairmatching.controller;

import pairmatching.domain.*;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class PairController {
    private static final String PAIR_MENU_OPTION = "1";
    private static final String SEARCH_MENU_OPTION = "2";
    private static final String INITIALIZE_MENU_OPTION = "3";
    private static final String QUIT_MENU_OPTION = "Q";
    private static final String INVALID_INPUT_MESSAGE = "[ERROR] 잘못된 입력입니다.";
    private static final String BACKEND_FILE_PATH = "src/main/resources/backend-crew.md";
    private static final String FRONTEND_FILE_PATH = "src/main/resources/frontend-crew.md";

    // 리팩토링시 아래 필드들 없애기
    private final CrewRepository crewRepository = new CrewRepository();
    private final PairMatcher pairMatcher = new PairMatcher(crewRepository);

    // supplier이용해 재입력 로직 구현
    // 현재 네/아니오 선택 시에도 메뉴 입력으로 넘어가짐 -> 리팩토링 필요
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
            if (menuInput.equals(PAIR_MENU_OPTION)) {
                matchPair();
                continue;
            }
            if (menuInput.equals(SEARCH_MENU_OPTION)) {
                searchPair();
                continue;
            }
            if (menuInput.equals(INITIALIZE_MENU_OPTION)) {
                initializePair();
                continue;
            }
            if (menuInput.equals(QUIT_MENU_OPTION)) {
                return;
            }
            throw new IllegalArgumentException(INVALID_INPUT_MESSAGE);
        }
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
        boolean matchingInfoExists = pairMatcher.hasPreviousMatchingInfo(selectedCourseLevelMission);

        if (matchingInfoExists) {
            String rematchingInput = InputView.readRematching();
            if (rematchingInput.equals("아니오")) {
                return;
            }
            if (!rematchingInput.equals("네")) {
                throw new IllegalArgumentException(INVALID_INPUT_MESSAGE);
            }
        }
        pairMatcher.matchPairs(selectedCourseLevelMission);

        MatchingInfo matchingResult = getMatchingInfo(selectedCourseLevelMission);
        OutputView.outputPairMatching(matchingResult.toString());
    }

    private CourseLevelMissionInput readAndParseCourseLevelMission() {
        String courseLevelMission = InputView.readCourseLevelMission();
        return InputParser.parseCourseLevelMission(courseLevelMission);
    }

    private MatchingInfo getMatchingInfo(CourseLevelMissionInput selectedCourseLevelMission) {
        Course selectedCourse = selectedCourseLevelMission.getCourse();
        Level selectedLevel = selectedCourseLevelMission.getLevel();
        Mission selectedMission = selectedCourseLevelMission.getMission();
        return pairMatcher.getMatchingInfoByCourseLevelMission(selectedCourse, selectedLevel, selectedMission);
    }

    static <T> T retryUntilSuccess(Supplier<T> supplier) {
        while(true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
