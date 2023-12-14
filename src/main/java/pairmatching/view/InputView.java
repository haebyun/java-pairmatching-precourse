package pairmatching.view;

import java.util.Arrays;
import java.util.List;
import pairmatching.view.console.ConsoleReader;
import pairmatching.view.console.ConsoleWriter;

public class InputView {
    private static final String READ_MISSION_NOTICE = "과정, 레벨, 미션을 선택하세요.";
    private static final String READ_MISSION_EXAMPLE = "ex) 백엔드, 레벨1, 자동차경주";
    private static final String SEPARATOR = ", ";

    public List<String> readMission() {
        ConsoleWriter.printlnMessage(READ_MISSION_NOTICE);
        ConsoleWriter.printlnMessage(READ_MISSION_EXAMPLE);
        String message = ConsoleReader.enterMessage();
        return parseStringToList(message, SEPARATOR);
    }

    private static List<String> parseStringToList(String message, String separator) {
        return Arrays.stream(split(message, separator)).toList();
    }

    private static String[] split(String message, String separator) {
        return message.split(separator, -1);
    }
}
