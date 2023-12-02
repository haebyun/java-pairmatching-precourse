package pairmatching;

import pairmatching.domain.*;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        CrewRepository repository = new CrewRepository();
        // 크루 목록 로드
        repository.loadCrewsFromFiles("src/main/resources/backend-crew.md", "src/main/resources/frontend-crew.md");

        Course selectedCourse = Course.FRONTEND;

        List<Crew> selectedCrews = repository.getCrewsByCourse(selectedCourse);
        repository.shuffleCrews(selectedCrews);

        PairMatcher pairMatcher = new PairMatcher();
        List<Pair> pairs = pairMatcher.matchPairs(repository.getCrews());

        // 페어 출력 로직
        pairs.forEach(System.out::println);
    }
}
