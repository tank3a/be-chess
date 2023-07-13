package chess.model.pieces;

import chess.controller.Direction;
import chess.model.Position;

import java.util.List;
import java.util.stream.Collectors;

public class King extends Piece {

    King(PieceColor color) {
        super(color, PieceType.KING);
    }


    @Override
    public boolean verifyMovePosition(Position position, Position positionToMove) {
        List<Direction> movableDirection = Direction.everyDirection();

        List<Position> movablePosition = movableDirection
                .stream()
                .map(direction -> position.getPositionAfterDirection(direction))
                .collect(Collectors.toList());

        return movablePosition.contains(positionToMove);
    }
}
