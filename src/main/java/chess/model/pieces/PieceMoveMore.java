package chess.model.pieces;

import chess.controller.Direction;
import chess.exception.InvalidPositionException;
import chess.model.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class PieceMoveMore extends Piece {

    protected PieceMoveMore(PieceColor color, PieceType type) {
        super(color, type);
    }

    protected List<Position> recursiveMove(Position current, Direction direction) {
        List<Position> movablePosition = new ArrayList<>();

        try {
            Position position = current.getPositionAfterDirection(direction);
            movablePosition.add(position);
            movablePosition.addAll(recursiveMove(position, direction));
        } catch (InvalidPositionException exception) {
            return movablePosition;
        }

        return movablePosition;
    }

}
