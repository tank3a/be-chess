package chess.model.pieces;

import chess.exception.InvalidMoveException;
import chess.exception.PieceNotExistException;
import chess.model.Position;
import chess.model.pieces.Piece;
import chess.model.pieces.PieceColor;
import chess.model.pieces.PieceCreator;
import chess.model.pieces.PieceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KnightTest {

    private Piece piece;
    private Position current;

    @BeforeEach
    void setUp() {
        piece = PieceCreator.createWhiteKnight();
        current = new Position(4, 4);
    }


    @Test
    @DisplayName("나이트 이동 검증")
    void moveKnight() {
        //given
        Position after1 = new Position(5, 6);
        Position after2 = new Position(2, 3);
        Position after3 = new Position(2, 4);
        Position after4 = new Position(1, 1);

        //when, then
        assertDoesNotThrow(() -> piece.verifyMovePosition(current, after1));
        assertDoesNotThrow(() -> piece.verifyMovePosition(current, after2));
        assertThrows(InvalidMoveException.class, () -> piece.verifyMovePosition(current, after3));
        assertThrows(InvalidMoveException.class, () -> piece.verifyMovePosition(current, after4));
    }

    @Test
    @DisplayName("색깔 검증")
    void checkColor() {
        //given
        Piece piece2 = PieceCreator.createBlackKnight();
        Piece noPiece = PieceCreator.createBlank();

        //when
        PieceColor black = PieceColor.BLACK;
        PieceColor white = PieceColor.WHITE;

        //then
        assertTrue(piece.isWhite());
        assertTrue(piece.compareColor(white));
        assertTrue(piece2.isBlack());
        assertTrue(piece2.compareColor(black));
        assertTrue(piece.isOppositeColor(piece2));
        assertThrows(PieceNotExistException.class, () -> piece.isOppositeColor(noPiece));
    }

    @Test
    @DisplayName("기물 타입 검증")
    void checkType() {
        //given
        Piece piece2 = PieceCreator.createBlackKnight();

        PieceType knight = PieceType.KNIGHT;

        assertTrue(piece.compareType(knight));
        assertFalse(piece.isPawn());
        assertEquals(knight.getWhiteRepresentation(), piece.getTypeInCharacter());
        assertEquals(knight.getBlackRepresentation(), piece2.getTypeInCharacter());

    }

    @Test
    @DisplayName("기물 점수 검증")
    void checkPoint() {
        //when
        PieceType knight = PieceType.KNIGHT;

        //then

        assertEquals(knight.getDefaultPoint(), piece.getPoint());
    }
}
