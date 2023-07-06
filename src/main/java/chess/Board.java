package chess;

import chess.pieces.Piece;
import chess.pieces.Rank;

import java.util.ArrayList;
import java.util.List;

import static chess.Position.convertXIndex;
import static chess.Position.convertYIndex;
import static chess.StringUtils.appendNewLine;

public class Board {

    private List<Rank> boardList;
    private final int BOARD_SIZE = 64;


    public void initialize() {
        initializeEmpty();

        boardList.get(0).setInitialBlackOther();
        boardList.get(1).setInitialBlackPawn();
        boardList.get(6).setInitialWhitePawn();
        boardList.get(7).setInitialWhiteOther();
    }

    public void initializeEmpty() {
        boardList = new ArrayList<>();

        for (int index = 0; index < 8; index++) {
            boardList.add(new Rank());
        }
    }

    public String showBoard() {
        return toString();
    }

    public int countAll() {
        return BOARD_SIZE - getPieceCount(Piece.createBlank());
    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
        boardList.stream().forEach(rank -> board.append(appendNewLine(rank.toString())));

        return board.toString();
    }

    public int getPieceCount(Piece pieceToCount) {
        int count = 0;
        for (Rank rank : boardList) {
            count += rank.countPiece(pieceToCount);
        }
        return count;
    }

    public Piece findPiece(String position) {
        return boardList.get(convertYIndex(position)).getPiece(convertXIndex(position));
    }

    public void move(String position, Piece pieceToMove) {
        boardList.get(convertYIndex(position)).setPiece(convertXIndex(position), pieceToMove);
    }
}
