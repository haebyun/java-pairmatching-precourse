package pairmatching.view;

import java.util.List;
import pairmatching.domain.Matching;
import pairmatching.domain.Pair;
import pairmatching.domain.constants.Course;
import pairmatching.domain.constants.Level;
import pairmatching.domain.constants.Mission;
import pairmatching.view.console.ConsoleWriter;

public class OutputView {
    private static final String LINE = "#############################################";
    private static final String COURSE = "과정 : %s";
    private static final String MISSION = "미션 :";
    private static final String LEVEL = "  - %s: %s"; // 레벨, 미션
    private static final String DELIMITER = " | ";

    public void printCourseAndMission() {
        ConsoleWriter.printlnMessage(LINE);
        ConsoleWriter.printlnFormat(COURSE, getCourses());
        ConsoleWriter.printlnMessage(MISSION);
        printLevelAndMissions();
        ConsoleWriter.printlnMessage(LINE);

    }

    private void printLevelAndMissions() {
        for (Level level : Level.values()) {
            ConsoleWriter.printlnFormat(
                    LEVEL,
                    level.getName(),
                    getMissions(level)
            );
        }
    }

    private String getMissions(Level level) {
        List<String> missions = level.getMissions()
                .stream()
                .map(Mission::getName)
                .toList();
        return String.join(DELIMITER, missions);
    }

    private String getCourses() {
        return String.join(DELIMITER, Course.getCourseNames());
    }

    public void printMatching(Matching matching) {
        ConsoleWriter.printlnMessage("페어 매칭 결과입니다.");
        for (Pair pair : matching.getMatching()) {
            ConsoleWriter.printlnMessage(
                    String.join(" : ", pair.getPair())
            );
        }
    }

    public void printOverMatching() {
        ConsoleWriter.printlnMessage("[ERROR] 매칭에 실패하여 다시 미션을 선택합니다.");
    }

    public void printNoMatching() {
        ConsoleWriter.printlnMessage("[ERROR] 매칭 이력이 없습니다.");
    }
}
