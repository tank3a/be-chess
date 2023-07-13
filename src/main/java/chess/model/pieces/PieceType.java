package chess.model.pieces;

public enum PieceType {

    PAWN('p', 1.0),
    ROOK('r', 5.0),
    KNIGHT('n', 2.5),
    BISHOP('b', 3.0),
    QUEEN('q', 9.0),
    KING('k', 0.0),
    NO_PIECE('.', 0.0);

    private char representation;
    private double defaultPoint;

    PieceType(char representation, double defaultPoint) {
        this.representation = representation;
        this.defaultPoint = defaultPoint;
    }
    public char getWhiteRepresentation() {
        if(this.equals(PieceType.NO_PIECE)) {
            return getRepresentation();
        }

        return representation;
    }

    public char getBlackRepresentation() {
        if(this.equals(PieceType.NO_PIECE)) {
            return getRepresentation();
        }

        return Character.toUpperCase(representation);
    }

    private char getRepresentation() {
        return this.representation;
    }

    public double getDefaultPoint() {
        return this.defaultPoint;
    }
}
