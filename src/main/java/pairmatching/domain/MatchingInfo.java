package pairmatching.domain;

import java.util.List;

public class MatchingInfo {
    private Course course;
    private Level level;
    private String missionName;
    private List<Pair> pairs;

    public MatchingInfo(Course course, Level level, String missionName, List<Pair> pairs) {
        this.course = course;
        this.level = level;
        this.missionName = missionName;
        this.pairs = pairs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Course: ").append(course.getName()).append("\n");
        sb.append("Level: ").append(level.getName()).append("\n");
        sb.append("Mission: ").append(missionName).append("\n");

        sb.append("Pairs:\n");
        for (Pair pair : pairs) {
            sb.append(pair).append("\n");
        }

        return sb.toString();
    }
}
