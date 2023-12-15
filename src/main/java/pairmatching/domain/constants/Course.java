package pairmatching.domain.constants;

import java.util.Arrays;
import java.util.List;
import pairmatching.global.exception.CustomException;
import pairmatching.global.exception.ErrorMessage;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String name;

    Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * 이름을 통해 Course 객체를 조회하는 메소드
     */
    public static Course from(String name) {
        return Arrays.stream(Course.values())
                .filter(element -> element.name.equals(name))
                .findFirst()
                .orElseThrow(() -> CustomException.from(ErrorMessage.INVALID_COURSE_ERROR));
    }

    public static List<String> getCourseNames() {
        return Arrays.stream(Course.values())
                .map(element -> element.name)
                .toList();
    }
}
