package pairmatching.controller;

import java.util.List;
import pairmatching.global.util.CustomFileReader;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairManager {
    private final InputView inputView;
    private final OutputView outputView;
    private final PairMatcher pairMatcher;

    public PairManager(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;

        List<String> backend = CustomFileReader.readFile("resources/backend-crew.md");
        List<String> frontend = CustomFileReader.readFile("resources/frontend-crew.md");
        this.pairMatcher = new PairMatcher(backend, frontend);
    }


}
