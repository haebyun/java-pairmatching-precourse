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
        // 이미 두 명의 멤버가 있을 경우 예외 처리 필요
        members.add(crew);
    }

    @Override
    public String toString() {
        return members.stream()
                .map(Crew::getName)
                .collect(Collectors.joining(" : "));
    }
}
