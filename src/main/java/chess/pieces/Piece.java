package chess.pieces;

public class Piece {
    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";
    public static final char WHITE_PAWN_REPRESENTATION = 'p';
    public static final char BLACK_PAWN_REPRESENTATION = 'P';
    public static final char WHITE_ROOK_REPRESENTATION = 'r';
    public static final char BLACK_ROOK_REPRESENTATION = 'R';
    public static final char WHITE_KNIGHT_REPRESENTATION = 'n';
    public static final char BLACK_KNIGHT_REPRESENTATION = 'N';
    public static final char WHITE_BISHOP_REPRESENTATION = 'b';
    public static final char BLACK_BISHOP_REPRESENTATION = 'B';
    public static final char WHITE_QUEEN_REPRESENTATION = 'q';
    public static final char BLACK_QUEEN_REPRESENTATION = 'Q';
    public static final char WHITE_KING_REPRESENTATION = 'k';
    public static final char BLACK_KING_REPRESENTATION = 'K';

    private final String color;
    private final char representation;

    private Piece(final String color, final char representation) {
        this.color = color;
        this.representation = representation;
    }

    private static Piece init(final String color, final char representation) {
        return new Piece(color, representation);
    }

    public String getColor() {
        return color;
    }

    public char getRepresentation() {
        return representation;
    }

    public static Piece createPawn(final String color) {
        return init(color, color.equals(WHITE_COLOR) ? WHITE_PAWN_REPRESENTATION : BLACK_PAWN_REPRESENTATION);
    }

    public static Piece createRook(final String color) {
        return init(color, color.equals(WHITE_COLOR) ? WHITE_ROOK_REPRESENTATION : BLACK_ROOK_REPRESENTATION);
    }

    public static Piece createKnight(final String color) {
        return init(color, color.equals(WHITE_COLOR) ? WHITE_KNIGHT_REPRESENTATION : BLACK_KNIGHT_REPRESENTATION);
    }

    public static Piece createBishop(final String color) {
        return init(color, color.equals(WHITE_COLOR) ? WHITE_BISHOP_REPRESENTATION : BLACK_BISHOP_REPRESENTATION);
    }

    public static Piece createQueen(final String color) {
        return init(color, color.equals(WHITE_COLOR) ? WHITE_QUEEN_REPRESENTATION : BLACK_QUEEN_REPRESENTATION);
    }

    public static Piece createKing(final String color) {
        return init(color, color.equals(WHITE_COLOR) ? WHITE_KING_REPRESENTATION : BLACK_KING_REPRESENTATION);
    }

    public boolean isWhite() {
        return this.color.equals(WHITE_COLOR);
    }

    public boolean isBlack() {
        return this.color.equals(BLACK_COLOR);
    }
}
