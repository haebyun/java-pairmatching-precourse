package pairmatching.domain;

import pairmatching.domain.classification.Course;
import pairmatching.domain.classification.Level;
import pairmatching.domain.classification.Mission;

public class InputParser {
    private static final String INVALID_INPUT_FORMAT_MESSAGE = "[ERROR] 입력 형식이 잘못되었습니다. '과정, 레벨, 미션' 형식으로 입력해주세요.";

    public static CourseLevelMissionInput parseCourseLevelMission(String input) {
        String[] parts = splitInput(input);
        validateInputParts(parts);

        Course course = parseCourse(parts[0]);
        Level level = parseLevel(parts[1]);
        Mission mission = parseMission(parts[2], level);

        return new CourseLevelMissionInput(course, level, mission);
    }

    private static String[] splitInput(String input) {
        return input.split(", ", -1);
    }

    private static void validateInputParts(String[] parts) {
        if (parts.length != 3) {
            throw new IllegalArgumentException(INVALID_INPUT_FORMAT_MESSAGE);
        }
    }

    private static Course parseCourse(String courseName) {
        try {
            return Course.fromCourseName(courseName);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private static Level parseLevel(String levelName) {
        try {
            return Level.fromLevelName(levelName);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private static Mission parseMission(String missionName, Level level) {
        try {
            return Mission.fromMissionNameAndLevel(missionName, level);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
