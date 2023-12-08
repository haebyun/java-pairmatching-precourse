package pairmatching.domain.classification;

public enum Mission {
    CAR_RACING(Level.LEVEL1, "자동차경주"),
    LOTTO(Level.LEVEL1, "로또"),
    NUMBER_BASEBALL(Level.LEVEL1, "숫자야구게임"),

    SHOPPING_CART(Level.LEVEL2, "장바구니"),
    PAYMENT(Level.LEVEL2, "결제"),
    SUBWAY_ROUTE(Level.LEVEL2, "지하철노선도"),

    PERFORMANCE_IMPROVEMENT(Level.LEVEL4, "성능개선"),
    DEPLOYMENT(Level.LEVEL4, "배포");

    private static final String INVALID_MISSION_MESSAGE = "[ERROR] 잘못된 미션 이름입니다.";
    private final Level level;
    private final String missionName;

    Mission(Level level, String missionName) {
        this.level = level;
        this.missionName = missionName;
    }

    public Level getLevel() {
        return level;
    }

    public String getMissionName() {
        return missionName;
    }

    public static Mission fromMissionNameAndLevel(String missionName, Level level) {
        for (Mission mission : values()) {
            if (mission.getMissionName().equals(missionName) && mission.getLevel() == level) {
                return mission;
            }
        }
        throw new IllegalArgumentException(INVALID_MISSION_MESSAGE);
    }
}
