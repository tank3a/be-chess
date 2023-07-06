package chess.pieces;

public class Piece {

    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }

    public enum Type {
        PAWN('p'), ROOK('r'), KNIGHT('n'), BISHOP('b'), QUEEN('q'), KING('k'), NO_PIECE('.');

        private char representation;

        Type(char representation) {
            this.representation = representation;
        }
        public char getWhiteRepresentation() {
            return representation;
        }

        public char getBlackRepresentation() {
            return Character.toUpperCase(representation);
        }
    }

    private final Color color;
    private final Type representation;

    private Piece(final Color color, final Type representation) {
        this.color = color;
        this.representation = representation;
    }

    private static Piece init(Color color, Type representation) {
        return new Piece(color, representation);
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return representation;
    }

    private static Piece createWhite(Type type) {
        return init(Color.WHITE, type);
    }

    private static Piece createBlack(Type type) {
        return init(Color.BLACK, type);
    }

    public static Piece createBlank() {
        return init(Color.NOCOLOR, Type.NO_PIECE);
    }

    public static Piece createBlackPawn() {
        return createBlack(Type.PAWN);
    }

    public static Piece createWhitePawn() {
        return createWhite(Type.PAWN);
    }

    public static Piece createBlackRook() {
        return createBlack(Type.ROOK);
    }

    public static Piece createWhiteRook() {
        return createWhite(Type.ROOK);
    }

    public static Piece createBlackKnight() {
        return createBlack(Type.KNIGHT);
    }

    public static Piece createWhiteKnight() {
        return createWhite(Type.KNIGHT);
    }

    public static Piece createBlackBishop() {
        return createBlack(Type.BISHOP);
    }

    public static Piece createWhiteBishop() {
        return createWhite(Type.BISHOP);
    }

    public static Piece createBlackQueen() {
        return createBlack(Type.QUEEN);
    }

    public static Piece createWhiteQueen() {
        return createWhite(Type.QUEEN);
    }

    public static Piece createBlackKing() {
        return createBlack(Type.KING);
    }

    public static Piece createWhiteKing() {
        return createWhite(Type.KING);
    }

    public boolean isWhite() {
        return this.color.equals(Color.WHITE);
    }

    public boolean isBlack() {
        return this.color.equals(Color.BLACK);
    }

    public char getRepresentation() {
        if(isBlack())
            return representation.getBlackRepresentation();

        return representation.getWhiteRepresentation();
    }
}
