package chess;

import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static chess.Position.convertXIndex;
import static chess.Position.convertYIndex;
import static chess.StringUtils.appendNewLine;

public class Board {

    class Rank {
        Piece[] rowList = new Piece[8];

        @Override
        public String toString() {
            StringBuilder rank = new StringBuilder();
            Arrays.stream(rowList).forEach(piece -> rank.append(piece.getTypeInCharacter()));

            return rank.toString();
        }
    }

    private List<Rank> boardList;


    public void initialize() {
        initializeEmpty();
        insertInitialBlackOthers();
        insertInitialBlackPawn();
        insertInitialWhiteOthers();
        insertInitialWhitePawn();
    }

    public void initializeEmpty() {
        boardList = new ArrayList<>() {{
            add(new Rank());
            add(new Rank());
            add(new Rank());
            add(new Rank());
            add(new Rank());
            add(new Rank());
            add(new Rank());
            add(new Rank());
        }};
        boardList.stream().forEach(rank -> Arrays.fill(rank.rowList, Piece.createBlank()));
    }

    private void insertInitialBlackPawn() {
        for(int index = 0; index < 8; index++) {
            boardList.get(1).rowList[index] = Piece.createBlackPawn();
        }
    }

    private void insertInitialWhitePawn() {
        for(int index = 0; index < 8; index++) {
            boardList.get(6).rowList[index] = Piece.createWhitePawn();
        }
    }

    private void insertInitialBlackOthers() {
        Rank rank = boardList.get(0);
        rank.rowList[0] = Piece.createBlackRook();
        rank.rowList[7] = Piece.createBlackRook();
        rank.rowList[1] = Piece.createBlackKnight();
        rank.rowList[6] = Piece.createBlackKnight();
        rank.rowList[2] = Piece.createBlackBishop();
        rank.rowList[5] = Piece.createBlackBishop();
        rank.rowList[3] = Piece.createBlackQueen();
        rank.rowList[4] = Piece.createBlackKing();
    }

    private void insertInitialWhiteOthers() {
        Rank rank = boardList.get(7);
        rank.rowList[0] = Piece.createWhiteRook();
        rank.rowList[7] = Piece.createWhiteRook();
        rank.rowList[1] = Piece.createWhiteKnight();
        rank.rowList[6] = Piece.createWhiteKnight();
        rank.rowList[2] = Piece.createWhiteBishop();
        rank.rowList[5] = Piece.createWhiteBishop();
        rank.rowList[3] = Piece.createWhiteQueen();
        rank.rowList[4] = Piece.createWhiteKing();
    }

    public String showBoard() {
        return toString();
    }

    public int countAll() {
        int count = 0;
        for(Rank rank : boardList) {
            for (Piece piece : rank.rowList) {
                if(piece.isWhite() || piece.isBlack())
                count++;
            }
        }

        return count;
    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
        boardList.stream().forEach(rank -> board.append(appendNewLine(rank.toString())));

        return board.toString();
    }

    public int getPieceCount(Piece pieceToCompare) {
        int count = 0;
        for (Rank rank : boardList) {
            for (Piece piece : rank.rowList) {
                if(piece.equals(pieceToCompare)) {
                    count++;
                }
            }
        }

        return count;
    }

    public Piece findPiece(String position) {
        return boardList.get(convertYIndex(position)).rowList[convertXIndex(position)];
    }

    public void move(String position, Piece pieceToMove) {
        boardList.get(convertYIndex(position)).rowList[convertXIndex(position)] = pieceToMove;
    }
}
