package pairmatching.domain;

import java.util.Objects;
import pairmatching.domain.constants.Course;
import pairmatching.domain.constants.Level;
import pairmatching.domain.constants.Mission;

/**
 * 과정과 미션을 저장하는 클래스
 */
public class CourseMission {
    private Course course;
    private Mission mission;

    public CourseMission(Course course, Mission mission) {
        this.course = course;
        this.mission = mission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseMission that = (CourseMission) o;
        return course == that.course && mission == that.mission;
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, mission);
    }

    public boolean equalLevel(Level level) {
        return mission.getLevel().equals(level);
    }
}
