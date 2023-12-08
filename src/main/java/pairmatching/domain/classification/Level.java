package pairmatching.domain.classification;

public enum Level {
    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    private static final String INVALID_LEVEL_MESSAGE = "[ERROR] 잘못된 레벨입니다.";
    private final String name;

    Level(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Level fromLevelName(String levelName) {
        for (Level level : Level.values()) {
            if (level.name.equals(levelName)) {
                return level;
            }
        }
        throw new IllegalArgumentException(INVALID_LEVEL_MESSAGE);
    }
}