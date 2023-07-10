package chess.pieces;

public class Piece {

    private final PieceColor color;
    private final PieceType type;

    private Piece(final PieceColor color, final PieceType type) {
        this.color = color;
        this.type = type;
    }

    private static Piece init(PieceColor color, PieceType representation) {
        return new Piece(color, representation);
    }

    private static Piece createWhite(PieceType type) {
        return init(PieceColor.WHITE, type);
    }

    private static Piece createBlack(PieceType type) {
        return init(PieceColor.BLACK, type);
    }

    public static Piece createBlank() {
        return init(PieceColor.NOCOLOR, PieceType.NO_PIECE);
    }

    public static Piece createBlackPawn() {
        return createBlack(PieceType.PAWN);
    }

    public static Piece createWhitePawn() {
        return createWhite(PieceType.PAWN);
    }

    public static Piece createBlackRook() {
        return createBlack(PieceType.ROOK);
    }

    public static Piece createWhiteRook() {
        return createWhite(PieceType.ROOK);
    }

    public static Piece createBlackKnight() {
        return createBlack(PieceType.KNIGHT);
    }

    public static Piece createWhiteKnight() {
        return createWhite(PieceType.KNIGHT);
    }

    public static Piece createBlackBishop() {
        return createBlack(PieceType.BISHOP);
    }

    public static Piece createWhiteBishop() {
        return createWhite(PieceType.BISHOP);
    }

    public static Piece createBlackQueen() {
        return createBlack(PieceType.QUEEN);
    }

    public static Piece createWhiteQueen() {
        return createWhite(PieceType.QUEEN);
    }

    public static Piece createBlackKing() {
        return createBlack(PieceType.KING);
    }

    public static Piece createWhiteKing() {
        return createWhite(PieceType.KING);
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
