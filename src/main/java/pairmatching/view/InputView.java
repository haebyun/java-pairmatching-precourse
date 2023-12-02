package pairmatching.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class InputView {
    public static final String LINE_SEPARATOR = System.lineSeparator();

    public static int selectMenuOption() {
        String menu = "기능을 선택하세요.\n"
                + "1. 페어 매칭\n"
                + "2. 페어 조회\n"
                + "3. 페어 초기화\n"
                + "Q. 종료";

        System.out.println(menu);

        int selectedMenu = readInt();
        System.out.print(LINE_SEPARATOR);

        return selectedMenu;
    }

    private static int readInt() {
        try {
            return Integer.parseInt(readLine());
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("에러문");
        }
    }

    public String readCourseLevelMission() {
        String menu = "#############################################\n"
                + "과정: 백엔드 | 프론트엔드\n"
                + "미션:\n"
                + "  - 레벨1: 자동차경주 | 로또 | 숫자야구게임\n"
                + "  - 레벨2: 장바구니 | 결제 | 지하철노선도\n"
                + "  - 레벨3: \n"
                + "  - 레벨4: 성능개선 | 배포\n"
                + "  - 레벨5: \n"
                + "#############################################\n"
                + "과정, 레벨, 미션을 선택하세요. (예: 백엔드, 레벨1, 자동차경주): ";

        System.out.print(menu);

        return readLine();
    }

    public String readRematching() {
        String ask = "매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n"
                + "네 | 아니오\n";

        System.out.print(ask);

        return readLine();
    }
}
