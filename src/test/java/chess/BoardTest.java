package chess;

import chess.pieces.Piece.Type;
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
                    .append(Type.ROOK.getBlackRepresentation())
                    .append(Type.KNIGHT.getBlackRepresentation())
                    .append(Type.BISHOP.getBlackRepresentation())
                    .append(Type.QUEEN.getBlackRepresentation())
                    .append(Type.KING.getBlackRepresentation())
                    .append(Type.BISHOP.getBlackRepresentation())
                    .append(Type.KNIGHT.getBlackRepresentation())
                    .append(Type.ROOK.getBlackRepresentation());
            String blackOtherRank = stringBuilder.toString();

            stringBuilder = new StringBuilder();
            stringBuilder
                    .append(Type.ROOK.getWhiteRepresentation())
                    .append(Type.KNIGHT.getWhiteRepresentation())
                    .append(Type.BISHOP.getWhiteRepresentation())
                    .append(Type.QUEEN.getWhiteRepresentation())
                    .append(Type.KING.getWhiteRepresentation())
                    .append(Type.BISHOP.getWhiteRepresentation())
                    .append(Type.KNIGHT.getWhiteRepresentation())
                    .append(Type.ROOK.getWhiteRepresentation());
            String whiteOtherRank = stringBuilder.toString();

            stringBuilder = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                stringBuilder.append(Type.PAWN.getBlackRepresentation());
            }
            String blackPawnRank = stringBuilder.toString();

            stringBuilder = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                stringBuilder.append(Type.PAWN.getWhiteRepresentation());
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
