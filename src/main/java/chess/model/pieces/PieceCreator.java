package chess.model.pieces;

public class PieceCreator {

    private PieceCreator() {}

    public static Piece createBlank() {
        return new NoPiece();
    }

    public static Piece createBlackPawn() {
        return new Pawn(PieceColor.BLACK);
    }

    public static Piece createWhitePawn() {
        return new Pawn(PieceColor.WHITE);
    }

    public static Piece createBlackRook() {
        return new Rook(PieceColor.BLACK);
    }

    public static Piece createWhiteRook() {
        return new Rook(PieceColor.WHITE);
    }

    public static Piece createBlackKnight() {
        return new Knight(PieceColor.BLACK);
    }

    public static Piece createWhiteKnight() {
        return new Knight(PieceColor.WHITE);
    }

    public static Piece createBlackBishop() {
        return new Bishop(PieceColor.BLACK);
    }

    public static Piece createWhiteBishop() {
        return new Bishop(PieceColor.WHITE);
    }

    public static Piece createBlackQueen() {
        return new Queen(PieceColor.BLACK);
    }

    public static Piece createWhiteQueen() {
        return new Queen(PieceColor.WHITE);
    }

    public static Piece createBlackKing() {
        return new King(PieceColor.BLACK);
    }

    public static Piece createWhiteKing() {
        return new King(PieceColor.WHITE);
    }

}
