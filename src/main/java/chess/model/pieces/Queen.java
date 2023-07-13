package chess.model.pieces;

import chess.controller.Direction;
import chess.model.Position;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    Queen(PieceColor color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    public boolean verifyMovePosition(Position position, Position positionToMove) {
        List<Direction> movableDirection = Direction.everyDirection();

        List<Position> movablePosition = new ArrayList<>();

        movableDirection.stream()
                .forEach(direction -> movablePosition.addAll(recursiveMove(position, direction)));

        return movablePosition.contains(positionToMove);

    }

    private List<Position> recursiveMove(Position current, Direction direction) {
        List<Position> movablePosition = new ArrayList<>();

        Position position = current.getPositionAfterDirection(direction);

        if(position != null) {
            movablePosition.add(position);
            movablePosition.addAll(recursiveMove(position, direction));
        }

        return movablePosition;
    }
}
