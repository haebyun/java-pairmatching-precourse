package pairmatching;

import pairmatching.controller.MatchingController;
import pairmatching.model.service.PairMatchingService;

public class Application {
    public static void main(String[] args) {
        // TODO 구현 진행
        MatchingController controller = new MatchingController(new PairMatchingService());
        controller.run();
    }
}
