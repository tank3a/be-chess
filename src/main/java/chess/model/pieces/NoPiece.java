package chess.model.pieces;

import chess.controller.Direction;
import chess.model.Position;

import java.util.List;

public class NoPiece extends Piece {

    NoPiece() {
        super(PieceColor.NOCOLOR, PieceType.NO_PIECE);
    }

    @Override
    public List<Direction> getPieceMovableDirection() {
        return null;
    }

    @Override
    public boolean verifyMovePosition(Position position, Position positionToMove) {
        return false;
    }
}
