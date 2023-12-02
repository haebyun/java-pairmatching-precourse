package pairmatching.model.constants;

import java.util.List;

public enum Level {
    LEVEL1("레벨1", List.of("자동차경주", "로또", "숫자야구게임")),
    LEVEL2("레벨2", List.of("장바구니", "결제", "지하철노선도")),
    LEVEL3("레벨3", List.of()),
    LEVEL4("레벨4", List.of("성능개선", "배포")),
    LEVEL5("레벨5", List.of());

    private final String name;
    private final List<String> missions;

    Level(String name, List<String> missions) {
        this.name = name;
        this.missions = missions;
    }

    public static String makeFormalizedInformations() {
        StringBuilder result = new StringBuilder();
        result.append("미션:\n");
        for (Level level : Level.values()) {
            result.append("  - ")
                    .append(level.name)
                    .append(": ")
                    .append(String.join(" | ", level.missions))
                    .append("\n");
        }
        return result.toString();
    }

    public static boolean isActualLevel(String input) {
        for (Level level : Level.values()) {
            if (level.name.equals(input)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isActualMission(String mission) {
        for (Level level : Level.values()) {
            if (level.missions.contains(mission)) {
                return true;
            }
        }
        return false;
    }
}
