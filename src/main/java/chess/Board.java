package chess;

import chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

import static chess.StringUtils.appendNewLine;

public class Board {

    private List<List<Piece>> boardList;

    public Board() {
        boardList = new ArrayList<>();
    }

    public void initialize() {
        boardList.add(createOtherList(Piece.BLACK_COLOR));
        boardList.add(createPawnList(Piece.BLACK_COLOR));
        boardList.add(new ArrayList<>());
        boardList.add(new ArrayList<>());
        boardList.add(new ArrayList<>());
        boardList.add(new ArrayList<>());
        boardList.add(createPawnList(Piece.WHITE_COLOR));
        boardList.add(createOtherList(Piece.WHITE_COLOR));
    }

    private List<Piece> createOtherList(final String color) {
        List<Piece> otherList = new ArrayList<>();
        otherList.add(Piece.createRook(color));
        otherList.add(Piece.createKnight(color));
        otherList.add(Piece.createBishop(color));
        otherList.add(Piece.createQueen(color));
        otherList.add(Piece.createKing(color));
        otherList.add(Piece.createBishop(color));
        otherList.add(Piece.createKnight(color));
        otherList.add(Piece.createRook(color));

        return otherList;
    }

    private List<Piece> createPawnList(final String color) {
        List<Piece> pawnList = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            pawnList.add(Piece.createPawn(color));
        }

        return pawnList;
    }

    public String showBoard() {
        StringBuilder boardPrint = new StringBuilder();
        for(int i = 0; i < 8; i++) {
            List<Piece> pieceList = boardList.get(i);
            if(pieceList.isEmpty()) {
                boardPrint.append(appendNewLine("........"));
                continue;
            }
            StringBuilder rank = new StringBuilder();
            pieceList.stream().forEach(piece -> rank.append(piece.getRepresentation()));
            boardPrint.append(appendNewLine(rank.toString()));
        }

        return boardPrint.toString();
    }

    public int count() {
        int count = 0;
        for(List<Piece> list : boardList) {
            count += list.size();
        }

        return count;
    }
}
