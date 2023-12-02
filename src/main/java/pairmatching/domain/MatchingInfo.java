package pairmatching.domain;

import java.util.List;
import java.util.stream.Collectors;

public class MatchingInfo {
    private Course course;
    private Level level;
    private Mission mission;
    private List<Pair> pairs;

    public MatchingInfo(Course course, Level level, Mission mission, List<Pair> pairs) {
        this.course = course;
        this.level = level;
        this.mission = mission;
        this.pairs = pairs;
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

    public List<Pair> getPairs() {
        return pairs;
    }

    public void updatePairs(List<Pair> pairs) {
        this.pairs = pairs;
    }

    @Override
    public String toString() {
        return pairs.stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }
}
