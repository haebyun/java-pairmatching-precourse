package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class Matching {
    private List<Pair> matching;

    public Matching() {
        this.matching = new ArrayList<>();
    }

    public void add(String name1, String name2) {
        Pair pair = new Pair(name1, name2);
        matching.add(pair);
    }

    public void addTriple(String first, String second, String third) {
        Pair pair = new Pair(first, second, third);
        matching.add(pair);
    }
}
