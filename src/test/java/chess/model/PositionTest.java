package chess.model;

import chess.controller.Direction;
import chess.exception.InvalidMoveException;
import chess.exception.InvalidPositionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    Position position;

    @BeforeEach
    void setUp() {
        position = new Position(5, 5);
    }

    @Test
    @DisplayName("위치 초기화")
    void initializeTest() {
        Position position1 = new Position("a8");
        Position position2 = new Position("c5");
        Position position3 = new Position("h1");

        assertEquals(new Position(0, 0), position1);
        assertEquals(new Position(2, 3), position2);
        assertEquals(new Position(7, 7), position3);
        assertThrows(InvalidPositionException.class, () -> new Position("i5"));
        assertThrows(InvalidPositionException.class, () -> new Position("b0"));
    }

    @Test
    @DisplayName("Position의 File을 출력한다.")
    void getFile() {
        //given
        position = new Position("c5");

        //when
        int file = position.getFile();

        //then
        assertEquals(2, file);
    }

    @Test
    @DisplayName("Position의 Rank를 출력한다.")
    void getRank() {
        position = new Position("c5");

        //when
        int rank = position.getRank();

        //then
        assertEquals(3, rank);
    }

    @Test
    @DisplayName("Direction이 주어졌을 때 이동한 위치를 출력한다.")
    void getPositionAfterDirection() {
        //given
        Direction direction1 = Direction.EAST;

        //when
        Position result1 = position.getPositionAfterDirection(direction1);

        //then
        assertEquals(new Position(6, 5), result1);

        //given
        Direction direction2 = Direction.NORTHEAST;
        position = new Position(7, 5);

        //when

        //then
        assertNull(position.getPositionAfterDirection(direction2));
    }
}