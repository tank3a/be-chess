package chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieceTest {

    @Nested
    class createPieceTest {
        @Test
        @DisplayName("흰색, 검정색 Piece 생성 테스트")
        public void create() {
            verifyPiece(Piece.createPawn(Piece.WHITE_COLOR), Piece.WHITE_COLOR, Piece.WHITE_PAWN_REPRESENTATION);
            verifyPiece(Piece.createPawn(Piece.BLACK_COLOR), Piece.BLACK_COLOR, Piece.BLACK_PAWN_REPRESENTATION);

            verifyPiece(Piece.createRook(Piece.WHITE_COLOR), Piece.WHITE_COLOR, Piece.WHITE_ROOK_REPRESENTATION);
            verifyPiece(Piece.createRook(Piece.BLACK_COLOR), Piece.BLACK_COLOR, Piece.BLACK_ROOK_REPRESENTATION);

            verifyPiece(Piece.createKnight(Piece.WHITE_COLOR), Piece.WHITE_COLOR, Piece.WHITE_KNIGHT_REPRESENTATION);
            verifyPiece(Piece.createKnight(Piece.BLACK_COLOR), Piece.BLACK_COLOR, Piece.BLACK_KNIGHT_REPRESENTATION);

            verifyPiece(Piece.createBishop(Piece.WHITE_COLOR), Piece.WHITE_COLOR, Piece.WHITE_BISHOP_REPRESENTATION);
            verifyPiece(Piece.createBishop(Piece.BLACK_COLOR), Piece.BLACK_COLOR, Piece.BLACK_BISHOP_REPRESENTATION);

            verifyPiece(Piece.createQueen(Piece.WHITE_COLOR), Piece.WHITE_COLOR, Piece.WHITE_QUEEN_REPRESENTATION);
            verifyPiece(Piece.createQueen(Piece.BLACK_COLOR), Piece.BLACK_COLOR, Piece.BLACK_QUEEN_REPRESENTATION);


            verifyPiece(Piece.createKing(Piece.WHITE_COLOR), Piece.WHITE_COLOR, Piece.WHITE_KING_REPRESENTATION);
            verifyPiece(Piece.createKing(Piece.BLACK_COLOR), Piece.BLACK_COLOR, Piece.BLACK_KING_REPRESENTATION);
        }

        private void verifyPiece(Piece piece, final String color, final char representation) {
            assertEquals(color, piece.getColor());
            assertEquals(representation, piece.getRepresentation());

            switch (color) {
                case Piece.WHITE_COLOR:
                    assertThat(piece.isWhite()).isTrue();
                    break;
                case Piece.BLACK_COLOR:
                    assertThat(piece.isBlack()).isTrue();
                    break;
            }
        }

    }

}
