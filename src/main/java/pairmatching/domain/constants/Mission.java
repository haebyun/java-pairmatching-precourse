package pairmatching.domain.constants;

import java.util.Arrays;
import pairmatching.global.exception.CustomException;
import pairmatching.global.exception.ErrorMessage;

public enum Mission {
    RACING_CAR("자동차경주", Level.LEVEL1),
    LOTTO("로또", Level.LEVEL1),
    BASEBALL("숫자야구게임", Level.LEVEL1),

    SHOPPING_BAG("장바구니", Level.LEVEL2),
    PAY("로또", Level.LEVEL2),
    SUBWAY_MAP("지하철노선도", Level.LEVEL2),

    PERFORMANCE("성능개선", Level.LEVEL3),
    DEPLOYMENT("배포", Level.LEVEL3);

    private final String name;
    private final Level level;

    Mission(String name, Level level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    /**
     * 이름을 통해 Mission 객체를 조회하는 메소드
     */
    public static Mission from(String name) {
        return Arrays.stream(Mission.values())
                .filter(element -> element.name.equals(name))
                .findFirst()
                .orElseThrow(() -> CustomException.from(ErrorMessage.INVALID_MISSION_ERROR));
    }
}
