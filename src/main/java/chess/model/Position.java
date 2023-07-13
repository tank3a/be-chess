package chess.model;

import chess.controller.Direction;
import chess.exception.ExceptionMessage;
import chess.exception.InvalidPositionException;

import java.util.Objects;

public class Position {

    private final int FILE;
    private final int RANK;
    private static final int MAX_RANK_SIZE = 8;
    private static final int MAX_FILE_SIZE = 8;

    public Position(String position) {
        FILE = position.charAt(0) - 'a';
        RANK = MAX_RANK_SIZE - Character.getNumericValue(position.charAt(1));

        if(!checkIsValid(FILE, RANK)) {
            throw new InvalidPositionException(ExceptionMessage.INVALID_POSITION);
        }
    }

    public Position(int file, int rank) {
        this.FILE = file;
        this.RANK = rank;

        if(!checkIsValid(this.FILE, this.RANK)) {
            throw new InvalidPositionException(ExceptionMessage.INVALID_POSITION);
        }
    }

    private boolean checkIsValid(int file, int rank) {
        return 0 <= file && file < MAX_FILE_SIZE && 0 <= rank && rank < MAX_RANK_SIZE;
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

        if(!checkIsValid(fileAfter, rankAfter)) {
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
