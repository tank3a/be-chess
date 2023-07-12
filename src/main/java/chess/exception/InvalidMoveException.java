package chess.exception;

public class InvalidMoveException extends RuntimeException {

    public InvalidMoveException(ExceptionMessageHandler message) {
        super(message.getMessage());
    }
}
