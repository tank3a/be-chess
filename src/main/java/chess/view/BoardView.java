package chess.view;

import chess.model.pieces.Piece;
import chess.model.board.Rank;

import java.util.Comparator;
import java.util.List;

import static chess.StringUtils.appendNewLine;

public class BoardView {

    public void showBoard(List<Rank> rankList) {
        StringBuilder board = new StringBuilder();
        rankList.forEach(rank -> board.append(appendNewLine(rank.getLinePrint())));

        System.out.println(board);
    }

    public void printScore(double blackScore, double whiteScore) {
        StringBuilder score = new StringBuilder();

        score.append("Black 점수: ").append(blackScore).append(appendNewLine(""));
        score.append("White 점수: ").append(whiteScore).append(appendNewLine(""));

        System.out.println(score);
    }

    public void printErrorInput(String message) {
        System.out.println(message);
    }

}
