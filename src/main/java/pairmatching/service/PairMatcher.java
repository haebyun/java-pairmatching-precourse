package pairmatching.service;

import java.util.List;
import pairmatching.controller.dto.Stage;

public class PairMatcher implements PairService {
    private final List<String> backend;
    private final List<String> frontend;

    public PairMatcher(List<String> backend, List<String> frontend) {
        this.backend = backend;
        this.frontend = frontend;
    }

    @Override
    public void execute(Stage stage) {
        
    }
}
