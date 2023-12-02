package pairmatching.controller;

import pairmatching.domain.*;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.util.List;

public class PairController {
    private CrewRepository crewRepository = new CrewRepository();
    PairMatcher pairMatcher = new PairMatcher(crewRepository);

    public void run() {
        // 크루 목록 로드
        crewRepository.loadCrewsFromFiles("src/main/resources/backend-crew.md", "src/main/resources/frontend-crew.md");

        while (true) {
            String menuInput = InputView.selectMenuOption();
            if (menuInput.equals("1")) {
                matchPair();
                continue;
            }
            if (menuInput.equals("2")) {
                searchPair();
                continue;
            }
            if (menuInput.equals("3")) {
                // 페어 초기화 로직
                continue;
            }
            if (menuInput.equalsIgnoreCase("Q")) {
                return;
            }
            System.out.println("[ERROR] 잘못된 입력입니다.");
        }
    }

    private void searchPair() {
        String courseLevelMission = InputView.readCourseLevelMission();
        CourseLevelMissionInput selectedCourseLevelMission = InputParser.parseCourseLevelMission(courseLevelMission);

        Course selectedCourse = selectedCourseLevelMission.getCourse();
        Level selectedLevel = selectedCourseLevelMission.getLevel();
        Mission selectedMission = selectedCourseLevelMission.getMission();

        // 매칭 결과 출력 로직
        MatchingInfo matchingResult = pairMatcher.getMatchingInfoByCourseLevelMission(selectedCourse, selectedLevel, selectedMission);
        OutputView.outputPairMatching(matchingResult.toString());
    }

    private void matchPair() {
        String courseLevelMission = InputView.readCourseLevelMission();
        CourseLevelMissionInput selectedCourseLevelMission = InputParser.parseCourseLevelMission(courseLevelMission);

        Course selectedCourse = selectedCourseLevelMission.getCourse();
        Level selectedLevel = selectedCourseLevelMission.getLevel();
        Mission selectedMission = selectedCourseLevelMission.getMission();

        // 매칭 진행
        pairMatcher.matchPairs(selectedCourse, selectedLevel, selectedMission);

        // 매칭 결과 출력 로직
        MatchingInfo matchingResult = pairMatcher.getMatchingInfoByCourseLevelMission(selectedCourse, selectedLevel, selectedMission);
        OutputView.outputPairMatching(matchingResult.toString());
    }
}
