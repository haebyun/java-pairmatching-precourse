package pairmatching.view;

import java.util.List;
import pairmatching.model.constants.Course;
import pairmatching.model.constants.Level;

public class OutputView {
    private static final String LINE = "#############################################";
    private static final String REQUEST_AND_EXAMPLE = "과정, 레벨, 미션을 선택하세요.\nex) 백엔드, 레벨1, 자동차경주";
    private static final String REMATCHING_REQUEST = "매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n네 | 아니오";
    private static final String NO_MATCHING_ERROR = "[ERROR] 매칭 이력이 없습니다.";
    private static final String RESET_COMPLETE = "초기화 되었습니다.";

    public static void printFeatureOptions() {
        System.out.println("기능을 선택하세요.");
        System.out.println("1. 페어 매칭");
        System.out.println("2. 페어 조회");
        System.out.println("3. 페어 초기화");
        System.out.println("Q. 종료");
    }

    public static void printSequenceInformations() {
        System.out.println();
        System.out.println(LINE);
        System.out.println(Course.makeCourseInformation());
        System.out.print(Level.makeFormalizedInformations());
        System.out.println(LINE);
    }

    public static void printRequestMessage() {
        System.out.println(REQUEST_AND_EXAMPLE);
    }

    public static void printMatchingResults(List<List<String>> results) {
        System.out.println();
        for (List<String> innerList : results) {
            String result = String.join(" : ", innerList);
            System.out.println(result);
        }
        System.out.println();
    }

    public static void printRematchingRequestMessage() {
        System.out.println(REMATCHING_REQUEST);
    }

    public static void printNoMatchingMessage() {
        System.out.println();
        System.out.println(NO_MATCHING_ERROR);
        System.out.println();
    }

    public static void printResetCompleteMessage() {
        System.out.println();
        System.out.println(RESET_COMPLETE);
        System.out.println();
    }

    public static void printErrorMessage(String message) {
        System.out.println("[ERROR]" + message);
    }
}
