package pairmatching.view;

import java.util.Arrays;
import java.util.List;
import pairmatching.controller.dto.Stage;
import pairmatching.domain.constants.Course;
import pairmatching.domain.constants.GameCommand;
import pairmatching.domain.constants.Level;
import pairmatching.domain.constants.Mission;
import pairmatching.view.console.ConsoleReader;
import pairmatching.view.console.ConsoleWriter;

public class InputView {
    private static final String READ_MISSION_NOTICE = "과정, 레벨, 미션을 선택하세요.";
    private static final String READ_MISSION_EXAMPLE = "ex) 백엔드, 레벨1, 자동차경주";
    private static final String SEPARATOR = ", ";

    public GameCommand readFunction() {
        ConsoleWriter.printlnMessage("기능을 선택하세요.");
        ConsoleWriter.printlnMessage("1. 페어 매칭");
        ConsoleWriter.printlnMessage("2. 페어 조회");
        ConsoleWriter.printlnMessage("3. 페어 초기화");
        ConsoleWriter.printlnMessage("Q. 종료");
        return GameCommand.from(
                ConsoleReader.enterMessage()
        );
    }

    public Stage readStage() {
        ConsoleWriter.printlnMessage(READ_MISSION_NOTICE);
        ConsoleWriter.printlnMessage(READ_MISSION_EXAMPLE);
        String message = ConsoleReader.enterMessage();
        List<String> infos = parseStringToList(message, SEPARATOR);
        return new Stage(
                Course.from(infos.get(0)),
                Level.from(infos.get(1)),
                Mission.from(infos.get(2))
        );
    }

    private static List<String> parseStringToList(String message, String separator) {
        return Arrays.stream(split(message, separator)).toList();
    }

    private static String[] split(String message, String separator) {
        return message.split(separator, -1);
    }


}
