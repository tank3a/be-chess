package chess.model.pieces;

import chess.controller.Direction;
import chess.exception.ExceptionMessage;
import chess.exception.InvalidMoveException;
import chess.exception.InvalidPositionException;
import chess.model.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class PieceMoveMore extends Piece {

    protected PieceMoveMore(PieceColor color, PieceType type) {
        super(color, type);
    }

    protected abstract List<Direction> getPieceMovableDirection();

    @Override
    public void verifyMovePosition(Position position, Position positionToMove) {
        List<Direction> movableDirection = Direction.everyDirection();

        List<Position> movablePosition = new ArrayList<>();

        movableDirection.stream()
                .forEach(direction -> movablePosition.addAll(recursiveMove(position, direction)));

        if(!movablePosition.contains(positionToMove)) {
            throw new InvalidMoveException(ExceptionMessage.INVALID_MOVE);
        }

    }

    protected List<Position> recursiveMove(Position current, Direction direction) {
        List<Position> movablePosition = new ArrayList<>();

        try {
            Position position = current.getPositionAfterDirection(direction);
            movablePosition.add(position);
            movablePosition.addAll(recursiveMove(position, direction));
        } catch (Exception exception) {
            return movablePosition;
        }

        return movablePosition;
    }

}
