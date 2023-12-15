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

    /**
     * 두 매칭에서 중복되는 페어가 있는 지 검사하는 메소드
     */
    public boolean duplicated(Matching target) {
        for (Pair pair : matching) {
            if (target.contains(pair)) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(Pair target) {
        for (Pair pair : matching) {
            if (pair.contains(target.getPair())) {
                return true;
            }
        }
        return false;
    }

    public List<Pair> getMatching() {
        return matching;
    }
}
