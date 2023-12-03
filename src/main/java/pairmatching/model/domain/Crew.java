package pairmatching.model.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import pairmatching.model.constants.Course;

public class Crew {
    private final Map<String, Set<String>> matchingHistories = new HashMap<>();
    private final Course course;
    private final String name;

    public Crew(String name, Course course) {
        this.name = name;
        this.course = course;
    }

    public void addMatchingHistory(String level, String otherName) {
        matchingHistories
                .computeIfAbsent(level, key -> new HashSet<>())
                .add(otherName);
    }

    public boolean hasMatchingHistory(String level, String name) {
        if (!matchingHistories.containsKey(level)) {
            return false;
        }
        return matchingHistories.get(level).contains(name);
    }

    public void resetHistories() {
        this.matchingHistories.clear();
    }
}
