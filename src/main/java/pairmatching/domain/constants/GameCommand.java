package pairmatching.domain.constants;

import java.util.Arrays;
import pairmatching.global.exception.CustomException;
import pairmatching.global.exception.ErrorMessage;

public enum GameCommand {
    MATCHING("1"),
    INQUIRE("2"),
    RESET("3"),
    QUIT("Q");

    private final String command;

    GameCommand(String command) {
        this.command = command;
    }

    public static GameCommand from(String command) {
        return Arrays.stream(GameCommand.values())
                .filter(element -> element.command.equals(command))
                .findFirst()
                .orElseThrow(() -> CustomException.from(ErrorMessage.INVALID_COMMAND_ERROR));
    }
}
