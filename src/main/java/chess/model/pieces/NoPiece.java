package chess.model.pieces;

import chess.model.Position;

public class NoPiece extends Piece {

    NoPiece() {
        super(PieceColor.NOCOLOR, PieceType.NO_PIECE);
    }

    @Override
    public boolean verifyMovePosition(Position position, Position positionToMove) {
        return false;
    }
}
