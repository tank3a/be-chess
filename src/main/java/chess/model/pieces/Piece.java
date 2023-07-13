package chess.model.pieces;

import chess.controller.Direction;
import chess.exception.ExceptionMessage;
import chess.exception.PieceNotExistException;
import chess.model.Position;

import java.util.List;

public abstract class Piece {

    private final PieceColor color;
    private final PieceType type;


    protected Piece(final PieceColor color, final PieceType type) {
        this.color = color;
        this.type = type;
    }

    protected abstract List<Direction> getPieceMovableDirection();

    public abstract void verifyMovePosition(Position position, Position positionToMove);

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

    public boolean isOppositeColor(Piece piece) {
        if((this.color == PieceColor.NO_COLOR) || (piece.color == PieceColor.NO_COLOR)) {
            throw new PieceNotExistException(ExceptionMessage.INVALID_PIECE_COMPARE);
        }

        return this.color != piece.color;
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
