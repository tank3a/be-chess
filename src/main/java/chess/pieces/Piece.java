package chess.pieces;

public class Piece {

    private final PieceColor color;
    private final PieceType type;

    protected Piece(final PieceColor color, final PieceType type) {
        this.color = color;
        this.type = type;
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
