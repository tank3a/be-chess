package chess.model.pieces;

import chess.model.Position;

public class Knight extends Piece {

    Knight(PieceColor color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    public boolean verifyMovePosition(Position position, Position positionToMove) {
        return false;
    }
}
