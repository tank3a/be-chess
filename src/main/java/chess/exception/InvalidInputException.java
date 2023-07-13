package chess.exception;

public class InvalidInputException extends RuntimeException {

    public InvalidInputException(ExceptionMessage message) {
        super(message.getMessage());
    }
}
