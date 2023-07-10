package chess;

import chess.Board;
import chess.BoardView;
import org.junit.jupiter.api.BeforeEach;

public class BoardViewTest {

    private BoardView boardView;
    private Board board;

    @BeforeEach
    void init() {
        boardView = new BoardView();
        board = new Board();
    }

}
