package pairmatching.domain;

import java.util.HashSet;
import java.util.Set;

public class Pair {
    private Set<String> pair;

    public Pair(String first, String second) {
        this.pair = new HashSet<>();
        pair.add(first);
        pair.add(second);
    }

    public Pair(String first, String second, String third) {
        this.pair = new HashSet<>();
        pair.add(first);
        pair.add(second);
        pair.add(third);
    }
}
