package chess.model;

import chess.controller.Direction;
import chess.model.board.Board;

import java.util.Objects;

public class Position {

    private final int FILE;
    private final int RANK;

    public Position(String position) {
        FILE = position.charAt(0) - 'a';
        RANK = 8 - Character.getNumericValue(position.charAt(1));
    }

    public Position(int file, int rank) {
        FILE = file;
        RANK = rank;
    }

    public int getFile() {
        return FILE;
    }

    public int getRank() {
        return RANK;
    }

    public Position getPositionAfterDirection(Direction direction) {
        int fileAfter = FILE + direction.getXIndex();
        int rankAfter = RANK + direction.getYIndex();

        if(fileAfter >= Board.FILE_SIZE || fileAfter < 0 || rankAfter >= Board.RANK_SIZE || rankAfter < 0) {
            return null;
        }
        return new Position(fileAfter, rankAfter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return FILE == position.FILE && RANK == position.RANK;
    }

    @Override
    public int hashCode() {
        return Objects.hash(FILE, RANK);
    }
}
