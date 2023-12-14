package pairmatching.domain.constants;

import java.util.Arrays;
import pairmatching.global.exception.CustomException;
import pairmatching.global.exception.ErrorMessage;

public enum Level {
    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    private String name;

    Level(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * 이름을 통해 Level 객체를 조회하는 메소드
     */
    public static Level from(String name) {
        return Arrays.stream(Level.values())
                .filter(element -> element.name.equals(name))
                .findFirst()
                .orElseThrow(() -> CustomException.from(ErrorMessage.INVALID_LEVEL_ERROR));
    }
}
