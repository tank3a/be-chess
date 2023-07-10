package chess.model.pieces;

import chess.model.Position;

public class Bishop extends Piece {

    Bishop(PieceColor color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public boolean verifyMovePosition(Position position, Position positionToMove) {
        return false;
    }
}
