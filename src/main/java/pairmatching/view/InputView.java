package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static String getFeatureOption() {
        OutputView.printFeatureOptions();
        return Console.readLine();
    }

    public static String getSequenceOfOptions() {
        OutputView.printRequestMessage();
        return Console.readLine();
    }

    public static String getRematchingOption() {
        System.out.println();
        OutputView.printRematchingRequestMessage();
        return Console.readLine();
    }
}
