package pairmatching.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import pairmatching.controller.dto.Stage;
import pairmatching.domain.constants.Course;
import pairmatching.domain.constants.Mission;

public class Matchings {
    private Map<CourseMission, Matching> matchings;

    public Matchings() {
        matchings = new HashMap<>();
    }

    public void add(Stage stage, Matching matching) {
        CourseMission courseMission = new CourseMission(stage.course(), stage.mission());
        matchings.put(courseMission, matching);
    }

    public boolean hasMatching(Course course, Mission mission) {
        return matchings.containsKey(new CourseMission(course, mission));
    }

    public boolean isDuplicatedMatching(Stage stage, Matching target) {
        for (Entry<CourseMission, Matching> entry : matchings.entrySet()) {
            CourseMission courseMission = entry.getKey();
            Matching matching = entry.getValue();
            if (courseMission.equalLevel(stage.level()) && matching.duplicated(target)) {
                return true;
            }
        }
        return false;
    }

    public Optional<Matching> getMatching(Course course, Mission mission) {
        CourseMission courseMission = new CourseMission(course, mission);
        if (matchings.containsKey(courseMission)) {
            return Optional.of(matchings.get(courseMission));
        }
        return Optional.empty();
    }
}
