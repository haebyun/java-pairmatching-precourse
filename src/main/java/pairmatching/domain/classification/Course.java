package pairmatching.domain.classification;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private static final String INVALID_COURSE_MESSAGE = "[ERROR] 잘못된 과정 이름입니다.";
    private final String name;

    Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Course fromCourseName(String courseName) {
        for (Course course : Course.values()) {
            if (course.name.equals(courseName)) {
                return course;
            }
        }
        throw new IllegalArgumentException(INVALID_COURSE_MESSAGE);
    }
}