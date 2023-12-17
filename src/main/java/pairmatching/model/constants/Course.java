package pairmatching.model.constants;

import java.util.ArrayList;
import java.util.List;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private final String name;

    Course(String name) {
        this.name = name;
    }

    public static String makeCourseInformation() {
        StringBuilder result = new StringBuilder();
        result.append("과정: ");
        List<String> names = new ArrayList<>();
        for (Course course : Course.values()) {
            names.add(course.name);
        }
        result.append(String.join(" | ", names));
        return result.toString();
    }

    public static boolean isCourse(String name) {
        for (Course course : Course.values()) {
            if (course.name.equals(name)) {
                return true;
            }
        }
        return false;
    }
}
