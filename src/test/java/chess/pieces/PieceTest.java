package chess.pieces;

import chess.model.pieces.Piece;
import chess.model.pieces.PieceCreator;
import chess.model.pieces.PieceType;
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
            verifyPiece(PieceCreator.createWhitePawn(), PieceCreator.createBlackPawn(), PieceType.PAWN);
            verifyPiece(PieceCreator.createWhiteRook(), PieceCreator.createBlackRook(), PieceType.ROOK);
            verifyPiece(PieceCreator.createWhiteKnight(), PieceCreator.createBlackKnight(), PieceType.KNIGHT);
            verifyPiece(PieceCreator.createWhiteBishop(), PieceCreator.createBlackBishop(), PieceType.BISHOP);
            verifyPiece(PieceCreator.createWhiteKing(), PieceCreator.createBlackKing(), PieceType.KING);
            verifyPiece(PieceCreator.createWhiteQueen(), PieceCreator.createBlackQueen(), PieceType.QUEEN);

            Piece piece = PieceCreator.createBlank();
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
