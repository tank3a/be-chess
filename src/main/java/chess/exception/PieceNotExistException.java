package chess.exception;

public class PieceNotExistException extends RuntimeException {

    PieceNotExistException(ExceptionMessageHandler message) {
        super(message.getMessage());
    }
}
