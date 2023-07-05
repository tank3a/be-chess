package chess.pieces;

import chess.pieces.Piece.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {

    @Nested
    class elementTest {
        @Test
        @DisplayName("Piece Representation 테스트")
        public void pieceRepresentation() {
            assertEquals('p', Type.PAWN.getWhiteRepresentation());
            assertEquals('P', Type.PAWN.getBlackRepresentation());
        }
    }

    @Nested
    class createPieceTest {
        @Test
        @DisplayName("흰색, 검정색 Piece 생성 테스트")
        public void create_piece() {
            verifyPiece(Piece.createWhitePawn(), Piece.createBlackPawn(), Type.PAWN);
            verifyPiece(Piece.createWhiteRook(), Piece.createBlackRook(), Type.ROOK);
            verifyPiece(Piece.createWhiteKnight(), Piece.createBlackKnight(), Type.KNIGHT);
            verifyPiece(Piece.createWhiteBishop(), Piece.createBlackBishop(), Type.BISHOP);
            verifyPiece(Piece.createWhiteKing(), Piece.createBlackKing(), Type.KING);
            verifyPiece(Piece.createWhiteQueen(), Piece.createBlackQueen(), Type.QUEEN);

            Piece piece = Piece.createBlank();
            assertFalse(piece.isBlack());
            assertFalse(piece.isWhite());
            assertEquals(Type.NO_PIECE, piece.getType());
        }

        private void verifyPiece(Piece whitePiece, Piece blackPiece, Type type) {
            assertTrue(whitePiece.isWhite());
            assertEquals(type, whitePiece.getType());

            assertTrue(blackPiece.isWhite());
            assertEquals(type, blackPiece.getType());
        }
    }

}
