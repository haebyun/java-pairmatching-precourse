package pairmatching.domain.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pairmatching.global.exception.CustomException;
import pairmatching.global.exception.ErrorMessage;

public enum Level {
    LEVEL1("레벨1", List.of(Mission.RACING_CAR, Mission.LOTTO, Mission.BASEBALL)),
    LEVEL2("레벨2", List.of(Mission.SHOPPING_BAG, Mission.PAY, Mission.SUBWAY_MAP)),
    LEVEL3("레벨3", new ArrayList<>()),
    LEVEL4("레벨4", List.of(Mission.PERFORMANCE, Mission.DEPLOYMENT)),
    LEVEL5("레벨5", new ArrayList<>());

    private final String name;
    private final List<Mission> missions;

    Level(String name, List<Mission> missions) {
        this.name = name;
        this.missions = missions;
    }

    public String getName() {
        return name;
    }

    public List<Mission> getMissions() {
        return missions;
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
