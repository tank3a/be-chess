package chess.model;

import chess.controller.Direction;
import chess.exception.ExceptionMessage;
import chess.exception.InvalidPositionException;

import java.util.Objects;

public class Position {

    private final int file;
    private final int rank;
    private static final int MAX_RANK_SIZE = 8;
    private static final int MAX_FILE_SIZE = 8;

    public Position(String position) {
        file = position.charAt(0) - 'a';
        rank = MAX_RANK_SIZE - Character.getNumericValue(position.charAt(1));

        if(!checkIsValid(file, rank)) {
            throw new InvalidPositionException(ExceptionMessage.INVALID_POSITION);
        }
    }

    public Position(int file, int rank) {
        this.file = file;
        this.rank = rank;

        if(!checkIsValid(this.file, this.rank)) {
            throw new InvalidPositionException(ExceptionMessage.INVALID_POSITION);
        }
    }

    private boolean checkIsValid(int file, int rank) {
        return 0 <= file && file < MAX_FILE_SIZE && 0 <= rank && rank < MAX_RANK_SIZE;
    }

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }

    public Position getPositionAfterDirection(Direction direction) {
        int fileAfter = file + direction.getXIndex();
        int rankAfter = rank + direction.getYIndex();

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
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
