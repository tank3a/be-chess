package chess.exception;

public class InvalidMoveException extends RuntimeException {

    public InvalidMoveException(ExceptionMessage message) {
        super(message.getMessage());
    }
}
