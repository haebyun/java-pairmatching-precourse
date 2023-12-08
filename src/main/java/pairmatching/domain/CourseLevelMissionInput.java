package pairmatching.domain;

import pairmatching.domain.classification.Course;
import pairmatching.domain.classification.Level;
import pairmatching.domain.classification.Mission;

public class CourseLevelMissionInput {
    private final Course course;
    private final Level level;
    private final Mission mission;

    public CourseLevelMissionInput(Course course, Level level, Mission mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    public Course getCourse() {
        return course;
    }

    public Level getLevel() {
        return level;
    }

    public Mission getMission() {
        return mission;
    }
}
