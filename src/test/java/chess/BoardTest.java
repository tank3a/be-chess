package chess;

import chess.pieces.Piece;
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
        board.initialize();
    }

    @Nested
    class createTest {

        @Test
        @DisplayName("Board Initialize 테스트")
        public void initialize() {
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
    }

    @Nested
    @DisplayName("Board의 Piece를 count하는 테스트")
    class countTest {

        @Test
        @DisplayName("초기 말 개수 : 32개")
        public void count() {
            assertEquals(32, board.countAll());
        }

        @Test
        @DisplayName("특정 말의 개수")
        public void countSpecific() {
            assertEquals(8, board.getPieceCount(Piece.Color.BLACK, Type.PAWN));
            assertEquals(2, board.getPieceCount(Piece.Color.BLACK, Type.ROOK));
            assertEquals(1, board.getPieceCount(Piece.Color.BLACK, Type.KING));
            assertEquals(2, board.getPieceCount(Piece.Color.BLACK, Type.KNIGHT));
            assertEquals(2, board.getPieceCount(Piece.Color.BLACK, Type.BISHOP));
            assertEquals(1, board.getPieceCount(Piece.Color.BLACK, Type.QUEEN));

            assertEquals(8, board.getPieceCount(Piece.Color.WHITE, Type.PAWN));
            assertEquals(2, board.getPieceCount(Piece.Color.WHITE, Type.ROOK));
            assertEquals(1, board.getPieceCount(Piece.Color.WHITE, Type.KING));
            assertEquals(2, board.getPieceCount(Piece.Color.WHITE, Type.KNIGHT));
            assertEquals(2, board.getPieceCount(Piece.Color.WHITE, Type.BISHOP));
            assertEquals(1, board.getPieceCount(Piece.Color.WHITE, Type.QUEEN));

        }
    }


}
