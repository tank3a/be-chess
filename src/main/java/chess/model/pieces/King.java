package chess.model.pieces;

import chess.controller.Direction;

import java.util.List;

public class King extends Piece {

    King(PieceColor color) {
        super(color, PieceType.KING);
    }


    @Override
    public List<Direction> getPieceMovableDirection() {
        return Direction.everyDirection();
    }
}
