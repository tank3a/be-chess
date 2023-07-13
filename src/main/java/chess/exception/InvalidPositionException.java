package chess.exception;

public class InvalidPositionException extends RuntimeException {

    public InvalidPositionException(ExceptionMessage message) {
        super(message.getMessage());
    }
}
