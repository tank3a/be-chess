package chess.model;

import chess.controller.Direction;
import chess.exception.ExceptionMessageHandler;
import chess.exception.InvalidMoveException;
import chess.exception.InvalidPositionException;

import java.util.Objects;

public class Position {

    private final int FILE;
    private final int RANK;
    private final int MAX_RANK_SIZE = 8;
    private final int MAX_FILE_SIZE = 8;

    public Position(String position) {
        FILE = position.charAt(0) - 'a';
        RANK = MAX_RANK_SIZE - Character.getNumericValue(position.charAt(1));

        if(!checkIsValid(FILE, RANK)) {
            throw new InvalidPositionException(ExceptionMessageHandler.INVALID_POSITION);
        }
    }

    public Position(int file, int rank) {
        FILE = file;
        RANK = rank;

        if(!checkIsValid(FILE, RANK)) {
            throw new InvalidPositionException(ExceptionMessageHandler.INVALID_POSITION);
        }
    }

    private boolean checkIsValid(int file, int rank) {
        return FILE < MAX_FILE_SIZE && RANK < MAX_RANK_SIZE && FILE >= 0 && RANK >= 0;
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
            throw new InvalidMoveException(ExceptionMessageHandler.INVALID_MOVE);
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
