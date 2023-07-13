package chess.exception;

public enum ExceptionMessage {

    INVALID_MOVE("이동할 수 없는 위치입니다."),
    INVALID_POSITION("지정할 수 없는 위치입니다."),
    PIECE_NOT_EXIST("기물이 존재하지 않습니다."),
    INVALID_PAWN_MOVE("Pawn이 이동할 수 없는 위치입니다."),
    PIECE_BETWEEN("이동하려는 위치에 다른 기물이 존재합니다."),
    SAME_PIECE("동일한 색의 기물은 잡을 수 없습니다."),
    NOT_YOUR_PIECE("자신의 턴에 자신의 기물만 이동시킬 수 있습니다."),
    INVALID_BOARD_SIZE("체스판 크기를 초과하였습니다.");

    private String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    String getMessage() {
        return message;
    }
}
