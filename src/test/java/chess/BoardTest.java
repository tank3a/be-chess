package chess;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import chess.pieces.Pawn;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    Pawn black;
    Pawn white;
    Board board;

    @Before
    public void create() {
        board = new Board();

        black = new Pawn(Pawn.BLACK_COLOR);
        white = new Pawn(Pawn.WHITE_COLOR);
    }

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
}
