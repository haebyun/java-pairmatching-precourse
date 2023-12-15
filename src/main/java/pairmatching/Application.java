package pairmatching;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.controller.PairManager;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Application {
    public static void main(String[] args) {
        PairManager pairManager = new PairManager(
                new InputView(),
                new OutputView()
        );
        pairManager.run();
        Console.close();
    }
}
