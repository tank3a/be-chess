package chess.model.pieces;

import chess.controller.Direction;
import chess.exception.ExceptionMessage;
import chess.exception.InvalidMoveException;
import chess.model.Position;

import java.util.List;
import java.util.stream.Collectors;

public abstract class PieceMoveOnce extends Piece {

    protected PieceMoveOnce(PieceColor color, PieceType type) {
        super(color, type);
    }

    protected abstract List<Direction> getPieceMovableDirection();

    @Override
    public void verifyMovePosition(Position position, Position positionToMove) {
        List<Direction> movableDirection = getPieceMovableDirection();
        List<Position> movablePosition = movableDirection
                .stream()
                .map(position::getPositionAfterDirection)
                .collect(Collectors.toList());

        if(!movablePosition.contains(positionToMove)) {
            throw new InvalidMoveException(ExceptionMessage.INVALID_MOVE);
        }
    }

}
