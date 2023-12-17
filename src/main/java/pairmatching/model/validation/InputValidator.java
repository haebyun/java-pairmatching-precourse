package pairmatching.model.validation;

import java.util.List;
import java.util.regex.Pattern;
import pairmatching.model.constants.Course;
import pairmatching.model.constants.Level;

public class InputValidator {
    public static void validateFeatureOption(String input) {
        if (!Pattern.matches("^[1-3]$", input)) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    public static void validateRematchingOption(String option) {
        if (!option.equals("네") && !option.equals("아니오")) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    public static void validateSequenceOfOptions(List<String> options) {
        validateInvalidOptionsSize(options);
        String course = options.get(0);
        String level = options.get(1);
        String mission = options.get(2);
        validateInvalidCourse(course);
        validateInvalidLevel(level);
        validateInvalidMission(mission);
    }

    private static void validateInvalidOptionsSize(List<String> options) {
        if (options.size() != 3) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    private static void validateInvalidCourse(String course) {
        if (!Course.isCourse(course)) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    private static void validateInvalidLevel(String level) {
        if (!Level.isActualLevel(level)) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    private static void validateInvalidMission(String mission) {
        if (!Level.isActualMission(mission)) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }


}
