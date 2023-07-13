package chess.model.pieces;

import chess.controller.Direction;

import java.util.List;

public class King extends PieceMoveOnce {

    King(PieceColor color) {
        super(color, PieceType.KING);
    }


    @Override
    protected List<Direction> getPieceMovableDirection() {
        return Direction.everyDirection();
    }
}
