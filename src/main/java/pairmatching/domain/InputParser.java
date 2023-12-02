package pairmatching.domain;

public class InputParser {
    public static CourseLevelMissionInput parseCourseLevelMission(String input) {
        String[] parts = input.split(", ");

        if (parts.length != 3) {
            return null;
        }

        String courseParts = parts[0];
        String levelParts = parts[1];
        String missionParts = parts[2];

        Course course = Course.fromCourseName(courseParts);
        Level level = Level.fromLevelName(levelParts);
        Mission mission = Mission.fromMissionNameAndLevel(missionParts, level);

        if (course == null || level == null || mission == null) {
            return null;
        }

        return new CourseLevelMissionInput(course, level, mission);
    }
}
