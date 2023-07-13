package chess.controller;

import chess.model.Position;

import java.util.Arrays;
import java.util.List;

public enum Direction {

    NORTH(0, -1),
    NORTHWEST(-1, -1),
    WEST(-1, 0),
    SOUTHWEST(-1, 1),
    SOUTH(0, 1),
    SOUTHEAST(1, 1),
    EAST(1, 0),
    NORTHEAST(1, -1),
    NORTH_NORTHEAST(1, -2),
    EAST_NORTHEAST(2, -1),
    EAST_SOUTHEAST(2, 1),
    SOUTH_SOUTHEAST(1, 2),
    SOUTH_SOUTHWEST(-1, 2),
    WEST_SOUTHWEST(-2, 1),
    WEST_NORTHWEST(-2, -1),
    NORTH_NORTHWEST(-1, -2);

    private int xIndex;
    private int yIndex;

    Direction(int xIndex, int yIndex) {
        this.xIndex = xIndex;
        this.yIndex = yIndex;
    }

    public int getXIndex() {
        return xIndex;
    }

    public int getYIndex() {
        return yIndex;
    }

    public static Direction findDirection(Position before, Position after) {
        int xIncrement = after.getFile() - before.getFile();
        int yIncrement = after.getRank() - before.getRank();

        int maxValue = Math.max(Math.abs(xIncrement), Math.abs(yIncrement));

        for (Direction direction : Direction.values()) {
            int xIndex = direction.getXIndex();
            int yIndex = direction.getYIndex();
            int xExpected = 0;
            int yExpected = 0;
            for (int times = 1; times <= maxValue; times++) {
                xExpected += xIndex;
                yExpected += yIndex;

                if (Math.abs(xIncrement) < Math.abs(xExpected) || Math.abs(yIncrement) < Math.abs(yExpected)) {
                    break;
                }

                if (xExpected == xIncrement && yExpected == yIncrement) {
                    return direction;
                }
            }
        }

        return null;

    }

    public static List<Direction> linearDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST);
    }

    public static List<Direction> diagonalDirection() {
        return Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }

    public static List<Direction> everyDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }

    public static List<Direction> knightDirection() {
        return Arrays.asList(NORTH_NORTHEAST, EAST_NORTHEAST, EAST_SOUTHEAST, SOUTH_SOUTHEAST, SOUTH_SOUTHWEST, WEST_SOUTHWEST, WEST_NORTHWEST, NORTH_NORTHWEST);
    }

    public static List<Direction> whitePawnDirection() {
        return Arrays.asList(NORTH, NORTHEAST, NORTHWEST);
    }

    public static List<Direction> blackPawnDirection() {
        return Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST);
    }


}
