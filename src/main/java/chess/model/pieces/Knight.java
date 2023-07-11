package chess.model.pieces;

import chess.controller.Direction;
import chess.model.Position;

import java.util.List;
import java.util.stream.Collectors;

public class Knight extends Piece {

    Knight(PieceColor color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    public boolean verifyMovePosition(Position position, Position positionToMove) {
        List<Direction> movableDirection = Direction.knightDirection();

        List<Position> movablePosition = movableDirection
                .stream()
                .map(direction -> position.getPositionAfterDirection(direction))
                .collect(Collectors.toList());

        return movablePosition.contains(positionToMove);

    }
}
