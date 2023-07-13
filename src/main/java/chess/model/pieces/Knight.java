package chess.model.pieces;

import chess.controller.Direction;

import java.util.List;

public class Knight extends PieceMoveOnce {

    Knight(PieceColor color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    protected List<Direction> getPieceMovableDirection() {
        return Direction.knightDirection();
    }
}
