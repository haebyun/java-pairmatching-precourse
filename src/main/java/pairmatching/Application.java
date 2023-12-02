package pairmatching;

import pairmatching.domain.*;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        CrewRepository repository = new CrewRepository();
        // 크루 목록 로드
        repository.loadCrewsFromFiles("src/main/resources/backend-crew.md", "src/main/resources/frontend-crew.md");

        Course selectedCourse = Course.FRONTEND;
        Level selectedLevel = Level.LEVEL1; // 선택한 레벨
        String selectedMission = "자동차경주"; // 선택한 미션

        // 매칭 진행
        PairMatcher pairMatcher = new PairMatcher(repository);
        pairMatcher.matchPairs(selectedCourse, selectedLevel, selectedMission);

        // 매칭 결과 출력 로직
        List<MatchingInfo> matchingResults = pairMatcher.getMatchingResults();
        matchingResults.forEach(System.out::println);
    }
}
