package chess.model.pieces;

import chess.controller.Direction;

import java.util.List;

public class Pawn extends Piece {

    Pawn(PieceColor color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public List<Direction> getPieceMovableDirection() {
        List<Direction> movableDirection;

        movableDirection = Direction.whitePawnDirection();

        if(this.isBlack()) {
            movableDirection = Direction.blackPawnDirection();
        }

        return movableDirection;
    }
}
