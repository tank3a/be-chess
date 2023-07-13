package chess.model.pieces;

import chess.controller.Direction;
import chess.model.Position;

import java.util.List;
import java.util.stream.Collectors;

public class Pawn extends Piece {

    Pawn(PieceColor color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public boolean verifyMovePosition(Position position, Position positionToMove) {
        List<Direction> movableDirection;

        movableDirection = Direction.whitePawnDirection();

        if(this.isBlack()) {
            movableDirection = Direction.blackPawnDirection();
        }


        List<Position> movablePosition = movableDirection
                .stream()
                .map(direction -> position.getPositionAfterDirection(direction))
                .collect(Collectors.toList());

        return movablePosition.contains(positionToMove);
    }
}
