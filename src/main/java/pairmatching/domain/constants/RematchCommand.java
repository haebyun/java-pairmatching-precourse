package pairmatching.domain.constants;

import java.util.Arrays;
import pairmatching.global.exception.CustomException;
import pairmatching.global.exception.ErrorMessage;

public enum RematchCommand {
    YES("네"),
    NO("아니오");

    private final String command;

    RematchCommand(String command) {
        this.command = command;
    }

    public static RematchCommand from(String command) {
        return Arrays.stream(RematchCommand.values())
                .filter(element -> element.command.equals(command))
                .findFirst()
                .orElseThrow(() -> CustomException.from(ErrorMessage.INVALID_COMMAND_ERROR));
    }
}
