package chess.model.pieces;

import chess.controller.Direction;
import chess.exception.ExceptionMessage;
import chess.exception.PieceNotExistException;
import chess.model.Position;

import java.util.List;

public class BlankPiece extends Piece {

    BlankPiece() {
        super(PieceColor.NO_COLOR, PieceType.NO_PIECE);
    }

    @Override
    protected List<Direction> getPieceMovableDirection() {
        return null;
    }

    @Override
    public void verifyMovePosition(Position position, Position positionToMove) {
        throw new PieceNotExistException(ExceptionMessage.PIECE_NOT_EXIST);
    }
}
