package chess;

import chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<Pawn> list;

    public Board() {
        list = new ArrayList<>();
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
}
