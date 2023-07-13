package chess.exception;

public class PieceNotExistException extends RuntimeException {

    public PieceNotExistException(ExceptionMessage message) {
        super(message.getMessage());
    }
}
