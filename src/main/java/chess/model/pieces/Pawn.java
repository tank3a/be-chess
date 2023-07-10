package chess.model.pieces;

import chess.model.Position;

public class Pawn extends Piece {

    Pawn(PieceColor color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public boolean verifyMovePosition(Position position, Position positionToMove) {
        return false;
    }
}
