package pairmatching.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import pairmatching.controller.dto.Stage;
import pairmatching.domain.Matching;
import pairmatching.domain.Matchings;
import pairmatching.domain.constants.Course;
import pairmatching.global.util.RandomShuffler;

public class PairService {
    private final List<String> backend;
    private final List<String> frontend;
    private final Matchings matchings;

    public PairService(List<String> backend, List<String> frontend) {
        this.backend = backend;
        this.frontend = frontend;
        this.matchings = new Matchings();
    }

    /**
     * 페어 매칭을 수행하는 메서드
     *
     * @return 매칭에 성공하면 Matching 객체를, 실패하면 Optional.empty()를 반환
     */
    public Optional<Matching> save(Stage stage) {
        List<String> shuffle = RandomShuffler.shuffle(getTarget(stage));
        Matching matching = match(shuffle);
        if (matchings.isDuplicatedMatching(stage, matching)) {
            return Optional.empty();
        }
        matchings.add(stage, matching);
        return Optional.of(matching);
    }

    private List<String> getTarget(Stage stage) {
        List<String> target = new ArrayList<>();
        if (stage.course().equals(Course.BACKEND)) {
            target = backend;
        }
        if (stage.course().equals(Course.BACKEND)) {
            target = frontend;
        }
        return target;
    }

    private Matching match(List<String> names) {
        Matching matching = new Matching();
        if (isOddSize(names)) {
            matchLastTripleCrews(matching, names);
            names = names.subList(0, names.size() - 3);
        }
        for (int i = 0; i < names.size(); i += 2) {
            String first = names.get(i);
            String second = names.get(i + 1);
            matching.add(first, second);
        }
        return matching;
    }

    private boolean isOddSize(List<String> names) {
        return names.size() % 2 != 0;
    }

    private void matchLastTripleCrews(
            Matching matching,
            List<String> names
    ) {
        int size = names.size();
        String first = names.get(size - 1);
        String second = names.get(size - 2);
        String third = names.get(size - 3);
        matching.addTriple(first, second, third);
    }

    /**
     * 이미 매칭되어 있는지 확인하는 메소드
     */
    public boolean hasMatching(Stage stage) {
        return matchings.hasMatching(stage.course(), stage.mission());
    }

    /**
     * 페어를 조회하는 메소드
     */
    public Optional<Matching> getMatching(Stage stage) {
        return matchings.getMatching(stage.course(), stage.mission());
    }
}
