package chess.model.board;

import chess.exception.InvalidBoardException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RankTest {

    private Rank rank;

    @Test
    @DisplayName("Rank 생성 테스트")
    void createRank() {
        rank = new Rank();

        System.out.println(rank.getLinePrint());
        assertEquals("........", rank.getLinePrint());
        assertThrows(InvalidBoardException.class, () -> new Rank(9));
    }
}
