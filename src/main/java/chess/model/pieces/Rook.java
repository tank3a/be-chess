package chess.model.pieces;

import chess.model.Position;

public class Rook extends Piece {

    Rook(PieceColor color) {
        super(color, PieceType.ROOK);
    }

    @Override
    public boolean verifyMovePosition(Position position, Position positionToMove) {
        return false;
    }
}
