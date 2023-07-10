package chess.model.pieces;

import chess.model.Position;

public class Queen extends Piece {

    Queen(PieceColor color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    public boolean verifyMovePosition(Position position, Position positionToMove) {
        return false;
    }
}
