package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.classification.Course;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.stream.Collectors;

public class CrewRepository {
    private List<Crew> crews = new ArrayList<>();

    public void loadCrewsFromFiles(String backendFilePath, String frontendFilePath) {
        loadCrewsFromFile(backendFilePath, Course.BACKEND);
        loadCrewsFromFile(frontendFilePath, Course.FRONTEND);
    }

    public void loadCrewsFromFile(String filePath, Course course) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String name : lines) {
                crews.add(new Crew(course, name));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Crew> getCrews() {
        return crews;
    }

    public void shuffleCrews(List<Crew> selectedCrews) {
        this.crews = Randoms.shuffle(selectedCrews);
    }
    public List<Crew> getCrewsByCourse(Course course) {
        return crews.stream()
                .filter(crew -> crew.getCourse() == course)
                .collect(Collectors.toList());
    }
}
