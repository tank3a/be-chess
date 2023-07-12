package chess.exception;

public enum ExceptionMessageHandler {

    INVALID_MOVE_MESSAGE("이동할 수 없는 위치입니다."),
    INVALID_POSITION_MESSAGE("지정할 수 없는 위치입니다.");

    public String message;

    ExceptionMessageHandler(String message) {
        this.message = message;
    }

    String getMessage() {
        return message;
    }
}
