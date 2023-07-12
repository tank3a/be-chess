package chess.model.pieces;

import chess.controller.Direction;
import chess.exception.InvalidPositionException;
import chess.model.Position;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Piece {

    private final PieceColor color;
    private final PieceType type;

    protected Piece(final PieceColor color, final PieceType type) {
        this.color = color;
        this.type = type;
    }

    public abstract List<Direction> getPieceMovableDirection();

    public boolean verifyMovePosition(Position position, Position positionToMove) {
        List<Direction> movableDirection = getPieceMovableDirection();
        List<Position> movablePosition = movableDirection
                .stream()
                .map(direction -> {
                    try {
                        return position.getPositionAfterDirection(direction);
                    } catch (InvalidPositionException exception) {
                        return null;
                    }
                })
                .collect(Collectors.toList());

        return movablePosition.contains(positionToMove);
    }

    public boolean isWhite() {
        return this.color.equals(PieceColor.WHITE);
    }

    public boolean isBlack() {
        return this.color.equals(PieceColor.BLACK);
    }

    public boolean compareColor(PieceColor color) {
        return this.color == color;
    }

    public boolean compareColor(Piece piece) {
        return piece.compareColor(this.color);
    }

    public boolean compareType(PieceType type) {
        return this.type == type;
    }

    public boolean isPawn() {
        return this.type == PieceType.PAWN;
    }

    public char getTypeInCharacter() {
        if(isBlack()) {
            return type.getBlackRepresentation();
        }

        return type.getWhiteRepresentation();
    }

    public double getPoint() {
        return type.getDefaultPoint();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Piece piece = (Piece) obj;
        return piece.type == type && piece.color == color;
    }
}
