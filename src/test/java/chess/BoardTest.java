package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import chess.pieces.Pawn;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    Pawn black;
    Pawn white;
    Board board;

    @BeforeEach
    public void init() {
        board = new Board();
        black = new Pawn(Pawn.BLACK_COLOR);
        white = new Pawn(Pawn.WHITE_COLOR);
    }

    @Nested
    class createTest {
        @Test
        @DisplayName("Board에 Pawn 배치 테스트")
        public void testInput() throws Exception {

            validateInsert(black);
            validateInsert(white);
        }

        private void validateInsert(Pawn pawn) {
            int size = board.size();
            board.add(pawn);
            assertEquals(size + 1, board.size());
        }

        @Test
        @DisplayName("Pawn리스트 출력")
        public void initialize() {
            board.initialize();
            assertEquals(getRequiredPawnList(Pawn.WHITE_COLOR), board.getWhitePawnsResult());
            assertEquals(getRequiredPawnList(Pawn.BLACK_COLOR), board.getBlackPawnsResult());
        }

        private String getRequiredPawnList(final String color) {
            StringBuilder stringBuilder = new StringBuilder();
            char representation = color.equals(Pawn.WHITE_COLOR) ? Pawn.WHITE_REPRESENTATION : Pawn.BLACK_REPRESENTATION;
            for(int i = 0; i < 8; i++) {
                stringBuilder.append(representation);
            }

            return stringBuilder.toString();
        }
    }

    @Nested
    class printTest {

        @Test
        @DisplayName("체스판 출력 테스트")
        public void printTest() {
            board.initialize();
            board.print();
        }
    }

}
