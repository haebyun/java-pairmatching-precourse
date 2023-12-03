package pairmatching.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.model.constants.Course;
import pairmatching.model.domain.Crew;

public class CrewMaker {
    public static Map<String, Crew> makeCrews(Course course) {
        if (course == Course.BACKEND) {
            return createCrews("src/main/resources/backend-crew.md", course);
        }
        if (course == Course.FRONTEND) {
            return createCrews("src/main/resources/frontend-crew.md", course);
        }
        return null;
    }

    private static Map<String, Crew> createCrews(String filePath, Course course) {
        try {
            Path path = Paths.get(filePath);
            List<String> names = Files.readAllLines(path);
            Map<String, Crew> crews = new HashMap<>();
            for (String name : names) {
                crews.put(name, new Crew(name, course));
            }
            return crews;
        } catch (IOException e) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }
}
