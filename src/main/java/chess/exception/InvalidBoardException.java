package chess.exception;

public class InvalidBoardException extends RuntimeException {

    public InvalidBoardException(ExceptionMessage message) {
        super(message.getMessage());
    }

}
