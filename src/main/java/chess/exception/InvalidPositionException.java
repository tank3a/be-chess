package chess.exception;

public class InvalidPositionException extends RuntimeException {

    public InvalidPositionException(ExceptionMessageHandler message) {
        super(message.getMessage());
    }
}
