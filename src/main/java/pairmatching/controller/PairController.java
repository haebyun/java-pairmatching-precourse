package pairmatching.controller;

import pairmatching.domain.*;
import pairmatching.view.InputView;

import java.util.List;

public class PairController {
    private CrewRepository crewRepository = new CrewRepository();
    public void run() {
        // 크루 목록 로드
        crewRepository.loadCrewsFromFiles("src/main/resources/backend-crew.md", "src/main/resources/frontend-crew.md");

        while (true) {
            String menuInput = InputView.selectMenuOption();
            if (menuInput.equals("1")) {
                matchingPair();
                continue;
            }
            if (menuInput.equals("2")) {
                searchingPair();
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

    private void searchingPair() {
    }

    private void matchingPair() {
        String courseLevelMission = InputView.readCourseLevelMission();
        CourseLevelMissionInput selectedCourseLevelMission = InputParser.parseCourseLevelMission(courseLevelMission);

        Course selectedCourse = selectedCourseLevelMission.getCourse();
        Level selectedLevel = selectedCourseLevelMission.getLevel();
        Mission selectedMission = selectedCourseLevelMission.getMission();

        // 매칭 진행
        PairMatcher pairMatcher = new PairMatcher(crewRepository);
        pairMatcher.matchPairs(selectedCourse, selectedLevel, selectedMission);

        // 매칭 결과 출력 로직
        List<MatchingInfo> matchingResults = pairMatcher.getMatchingResults();
        matchingResults.forEach(System.out::println);
    }
}
