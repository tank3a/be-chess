package chess.model.pieces;

import chess.controller.Direction;

import java.util.List;

public class Bishop extends PieceMoveMore {

    Bishop(PieceColor color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public List<Direction> getPieceMovableDirection() {
        return Direction.diagonalDirection();
    }
}
