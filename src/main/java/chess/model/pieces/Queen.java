package chess.model.pieces;

import chess.controller.Direction;

import java.util.List;

public class Queen extends PieceMoveMore {

    Queen(PieceColor color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    public List<Direction> getPieceMovableDirection() {
        return Direction.everyDirection();
    }

}
