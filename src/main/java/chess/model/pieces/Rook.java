package chess.model.pieces;

import chess.controller.Direction;

import java.util.List;

public class Rook extends PieceMoveMore {

    Rook(PieceColor color) {
        super(color, PieceType.ROOK);
    }

    @Override
    public List<Direction> getPieceMovableDirection() {
        return Direction.linearDirection();
    }
}
