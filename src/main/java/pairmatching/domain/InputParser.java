package pairmatching.domain;

public class InputParser {
    private static final String INVALID_INPUT_MESSAGE = "[ERROR] 잘못된 입력입니다.";
    public static CourseLevelMissionInput parseCourseLevelMission(String input) {
        String[] parts = splitInput(input);

        Course course = Course.fromCourseName(parts[0]);
        Level level = Level.fromLevelName(parts[1]);
        Mission mission = Mission.fromMissionNameAndLevel(parts[2], level);

        return new CourseLevelMissionInput(course, level, mission);
    }

    private static String[] splitInput(String input) {
        String[] parts = input.split(", ");
        if (parts.length != 3) {
            throw new IllegalArgumentException(INVALID_INPUT_MESSAGE);
        }
        return parts;
    }
}
