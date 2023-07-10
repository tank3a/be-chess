package chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {

    @Nested
    class elementTest {
        @Test
        @DisplayName("Piece Representation 테스트")
        void pieceRepresentation() {
            assertEquals('p', PieceType.PAWN.getWhiteRepresentation());
            assertEquals('P', PieceType.PAWN.getBlackRepresentation());
        }
    }

    @Nested
    class createPieceTest {
        @Test
        @DisplayName("흰색, 검정색 Piece 생성 테스트")
        void create_piece() {
            verifyPiece(Piece.createWhitePawn(), Piece.createBlackPawn(), PieceType.PAWN);
            verifyPiece(Piece.createWhiteRook(), Piece.createBlackRook(), PieceType.ROOK);
            verifyPiece(Piece.createWhiteKnight(), Piece.createBlackKnight(), PieceType.KNIGHT);
            verifyPiece(Piece.createWhiteBishop(), Piece.createBlackBishop(), PieceType.BISHOP);
            verifyPiece(Piece.createWhiteKing(), Piece.createBlackKing(), PieceType.KING);
            verifyPiece(Piece.createWhiteQueen(), Piece.createBlackQueen(), PieceType.QUEEN);

            Piece piece = Piece.createBlank();
            assertFalse(piece.isBlack());
            assertFalse(piece.isWhite());
            assertTrue(piece.compareType(PieceType.NO_PIECE));
        }

        private void verifyPiece(Piece whitePiece, Piece blackPiece, PieceType type) {
            assertTrue(whitePiece.isWhite());
            assertTrue(whitePiece.compareType(type));

            assertTrue(blackPiece.isBlack());
            assertTrue(blackPiece.compareType(type));
        }
    }

}
