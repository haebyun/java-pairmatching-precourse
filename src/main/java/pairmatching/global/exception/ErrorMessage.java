package pairmatching.global.exception;

public enum ErrorMessage {
    BLANK_INPUT_ERROR("빈 문자열이 입력되었습니다."),
    INVALID_MISSION_ERROR("잘못된 미션 입력입니다."),
    INVALID_LEVEL_ERROR("잘못된 레벨 입력입니다."),
    INVALID_COURSE_ERROR("잘못된 과정 입력입니다."),
    INVALID_COMMAND_ERROR("잘못된 명령을 입력하였습니다."),
    NOT_MATCHED_MISSION("입력한 레벨과 미션이 일치하지 않습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
