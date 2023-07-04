package chess;

import chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<Pawn> list;
    private List<List<Pawn>> boardList;
    private List<Pawn> whitePawnList;
    private List<Pawn> blackPawnList;

    public Board() {
        list = new ArrayList<>();
        boardList = new ArrayList<>();
    }

    //구현 필요
    public void initialize() {
        whitePawnList = createPawnList(Pawn.WHITE_COLOR);
        blackPawnList = createPawnList(Pawn.BLACK_COLOR);
        boardList.add(new ArrayList<>());
        boardList.add(blackPawnList);
        boardList.add(new ArrayList<>());
        boardList.add(new ArrayList<>());
        boardList.add(new ArrayList<>());
        boardList.add(new ArrayList<>());
        boardList.add(whitePawnList);
        boardList.add(new ArrayList<>());
    }

    private List<Pawn> createPawnList(final String color) {
        List<Pawn> pawnList = new ArrayList<>();

        for(int i = 0; i < 8; i++) {
            pawnList.add(new Pawn(color));
        }

        return pawnList;

    }

    public void add(Pawn pawn) {
        list.add(pawn);
    }

    public int size() {
        return list.size();
    }

    public Pawn findPawn(int index) {
        return list.get(index);
    }

    //구현 필요
    public void print() {
        StringBuilder boardPrint = new StringBuilder();
        for(int i = 0; i < 8; i++) {
            List<Pawn> rank = boardList.get(i);
            if(rank.isEmpty()) {
                boardPrint.append("........");
                boardPrint.append("\n");
                continue;
            }
            rank.stream().forEach(pawn -> boardPrint.append(pawn.getRepresentation()));
            boardPrint.append("\n");
        }

        System.out.println(boardPrint.toString());
    }

    public String getWhitePawnsResult() {
        return getPawnsResult(Pawn.WHITE_COLOR);
    }

    public String getBlackPawnsResult() {
        return getPawnsResult(Pawn.BLACK_COLOR);
    }

    private String getPawnsResult(final String color) {
        StringBuilder pawnsResult = new StringBuilder();

        List<Pawn> pawnList = color.equals(Pawn.WHITE_COLOR) ? whitePawnList : blackPawnList;

        pawnList.stream().forEach(pawn -> pawnsResult.append(pawn.getRepresentation()));
        return pawnsResult.toString();
    }

}
