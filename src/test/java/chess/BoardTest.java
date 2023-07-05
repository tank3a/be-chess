package chess;

import chess.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chess.StringUtils.appendNewLine;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    private Board board;

    @BeforeEach
    public void init() {
        board = new Board();
    }

    @Nested
    class createTest {

        @Test
        @DisplayName("Board Initialize 테스트")
        public void initialize() {
            board.initialize();
            String blankRank = appendNewLine("........");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder
                    .append(Piece.BLACK_ROOK_REPRESENTATION)
                    .append(Piece.BLACK_KNIGHT_REPRESENTATION)
                    .append(Piece.BLACK_BISHOP_REPRESENTATION)
                    .append(Piece.BLACK_QUEEN_REPRESENTATION)
                    .append(Piece.BLACK_KING_REPRESENTATION)
                    .append(Piece.BLACK_BISHOP_REPRESENTATION)
                    .append(Piece.BLACK_KNIGHT_REPRESENTATION)
                    .append(Piece.BLACK_ROOK_REPRESENTATION);
            String blackOtherRank = stringBuilder.toString();

            stringBuilder = new StringBuilder();
            stringBuilder
                    .append(Piece.WHITE_ROOK_REPRESENTATION)
                    .append(Piece.WHITE_KNIGHT_REPRESENTATION)
                    .append(Piece.WHITE_BISHOP_REPRESENTATION)
                    .append(Piece.WHITE_QUEEN_REPRESENTATION)
                    .append(Piece.WHITE_KING_REPRESENTATION)
                    .append(Piece.WHITE_BISHOP_REPRESENTATION)
                    .append(Piece.WHITE_KNIGHT_REPRESENTATION)
                    .append(Piece.WHITE_ROOK_REPRESENTATION);
            String whiteOtherRank = stringBuilder.toString();

            stringBuilder = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                stringBuilder.append(Piece.BLACK_PAWN_REPRESENTATION);
            }
            String blackPawnRank = stringBuilder.toString();

            stringBuilder = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                stringBuilder.append(Piece.WHITE_PAWN_REPRESENTATION);
            }
            String whitePawnRank = stringBuilder.toString();


            assertEquals(
                    appendNewLine(blackOtherRank) +
                    appendNewLine(blackPawnRank) +
                    blankRank + blankRank + blankRank + blankRank +
                    appendNewLine(whitePawnRank) +
                    appendNewLine(whiteOtherRank),
                    board.showBoard()
            );
        }

        @Test
        @DisplayName("초기 말 개수 : 32개")
        public void count() {
            board.initialize();
            assertEquals(32, board.count());
        }
    }

}
