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
            verifyPiece(Piece.createWhite(Type.PAWN), Piece.createBlack(Type.PAWN), Type.PAWN);
            verifyPiece(Piece.createWhite(Type.ROOK), Piece.createBlack(Type.ROOK), Type.ROOK);
            verifyPiece(Piece.createWhite(Type.KNIGHT), Piece.createBlack(Type.KNIGHT), Type.KNIGHT);
            verifyPiece(Piece.createWhite(Type.BISHOP), Piece.createBlack(Type.BISHOP), Type.BISHOP);
            verifyPiece(Piece.createWhite(Type.KING), Piece.createBlack(Type.KING), Type.KING);
            verifyPiece(Piece.createWhite(Type.QUEEN), Piece.createBlack(Type.QUEEN), Type.QUEEN);

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
