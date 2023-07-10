package chess.view;

import chess.model.pieces.Piece;
import chess.model.board.Rank;

import java.util.Comparator;
import java.util.List;

import static chess.StringUtils.appendNewLine;

public class BoardView {

    public String showBoard(List<Rank> rankList) {
        StringBuilder board = new StringBuilder();
        rankList.stream().forEach(rank -> board.append(appendNewLine(rank.getLinePrint())));

        return board.toString();
    }

    private StringBuilder printSort(List<Piece> pieceList) {
        StringBuilder stringBuilder = new StringBuilder();

        pieceList.stream().sorted(Comparator.comparing(piece -> piece.getPoint()))
                .forEach(piece -> stringBuilder.append(piece.getTypeInCharacter()));

        return stringBuilder;
    }

    public String printPieceAsc(List<Piece> pieceList) {

        StringBuilder stringBuilder = printSort(pieceList);

        return stringBuilder.toString();
    }

    public String printPieceDesc(List<Piece> pieceList) {
        StringBuilder stringBuilder = printSort(pieceList);

        return stringBuilder.reverse().toString();
    }

}
