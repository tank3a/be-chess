package chess.view;

import chess.model.board.Board;
import chess.model.board.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardViewTest {

    Board board;
    BoardView boardView;

    @BeforeEach
    void setUp() {
        board = new Board();
        boardView = new BoardView();
    }

    @Test
    @DisplayName("초기화된 체스판이 출력되어야 한다.")
    void showBoardTest() {
        //given
        board.initialize();
        List<Rank> rankList = board.getRankList();

        //when, then
        boardView.showBoard(rankList);
    }

    @Test
    @DisplayName("검정 점수 10점, 흰색 점수 5.5점이 출력되어야 한다.")
    void printScoreTest() {
        //given
        double blackScore = 10.0;
        double whiteScore = 5.5;

        //when, then
        boardView.printScore(blackScore, whiteScore);
    }

    @Test
    @DisplayName("에러1이 출력되어야 한다.")
    void printErrorInput() {
        //given
        Exception exception = new RuntimeException("에러1");

        //when, then
        boardView.printErrorInput(exception.getMessage());
    }
}