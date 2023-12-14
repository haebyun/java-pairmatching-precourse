package pairmatching.domain;

import java.util.HashMap;
import java.util.Map;
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
}
