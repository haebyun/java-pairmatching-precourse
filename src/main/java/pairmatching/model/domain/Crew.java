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

    public void addMatchingHistory(String level, String name) {
        if (!matchingHistories.containsKey(level)) {
            Set<String> set = new HashSet<>();
            set.add(name);
            matchingHistories.put(level, set);
            return;
        }
        Set<String> updatedSet = matchingHistories.get(level);
        updatedSet.add(name);
        matchingHistories.replace(level, updatedSet);
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
