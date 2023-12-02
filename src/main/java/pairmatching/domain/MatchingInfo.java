package pairmatching.domain;

import java.util.List;

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

    @Override
    public String toString() {
        return "MatchingInfo{" +
                "course=" + course +
                "\nlevel=" + level +
                "\nmission=" + mission +
                "\npairs=" + pairs +
                '}';
    }
}
