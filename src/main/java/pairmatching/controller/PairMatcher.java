package pairmatching.controller;

import java.util.List;

public class PairMatcher {
    private final List<String> backend;
    private final List<String> frontend;

    public PairMatcher(List<String> backend, List<String> frontend) {
        this.backend = backend;
        this.frontend = frontend;
    }
}
