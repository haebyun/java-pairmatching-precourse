package pairmatching.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Pair {
    private List<Crew> members;

    public Pair(Crew... members) {
        this.members = new ArrayList<>(Arrays.asList(members));
    }

    public void addCrew(Crew crew) {
        members.add(crew);
    }

    @Override
    public String toString() {
        return members.stream()
                .map(Crew::getName)
                .collect(Collectors.joining(" : "));
    }
}
