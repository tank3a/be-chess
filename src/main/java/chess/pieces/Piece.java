package chess.pieces;

public class Piece {

    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }

    public enum Type {
        PAWN('p', 1.0),
        ROOK('r', 5.0),
        KNIGHT('n', 2.5),
        BISHOP('b', 3.0),
        QUEEN('q', 9.0),
        KING('k', 0.0),
        NO_PIECE('.', 0.0);

        private char representation;
        private double defaultPoint;

        Type(char representation, double defaultPoint) {
            this.representation = representation;
            this.defaultPoint = defaultPoint;
        }
        public char getWhiteRepresentation() {
            return representation;
        }

        public char getBlackRepresentation() {
            return Character.toUpperCase(representation);
        }

        public double getPoint() {
            return defaultPoint;
        }
    }

    private final Color color;
    private final Type type;

    private Piece(final Color color, final Type type) {
        this.color = color;
        this.type = type;
    }

    private static Piece init(Color color, Type representation) {
        return new Piece(color, representation);
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
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

    public char getTypeInCharacter() {
        if(isBlack()) {
            return type.getBlackRepresentation();
        }

        return type.getWhiteRepresentation();
    }

    public double getScore() {
        return type.defaultPoint;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Piece piece = (Piece) obj;
        return piece.getType().equals(type) && piece.getColor().equals(color);
    }


}
