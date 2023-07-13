package chess.model.pieces;

import chess.controller.Direction;
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

public class BishopTest {

    private Piece piece;
    private Position current;

    @BeforeEach
    void setUp() {
        piece = PieceCreator.createWhiteBishop();
        current = new Position(4, 4);
    }


    @Test
    @DisplayName("비숍 이동 검증")
    void moveBishopDiagonal() {
        //given
        Position after1 = new Position(7, 7);
        Position after2 = new Position(1, 7);
        Position after3 = new Position(3, 4);
        Position after4 = new Position(6, 7);

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
        Piece piece2 = PieceCreator.createBlackBishop();
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
        Piece piece2 = PieceCreator.createBlackBishop();

        PieceType bishop = PieceType.BISHOP;

        assertTrue(piece.compareType(bishop));
        assertFalse(piece.isPawn());
        assertEquals(bishop.getWhiteRepresentation(), piece.getTypeInCharacter());
        assertEquals(bishop.getBlackRepresentation(), piece2.getTypeInCharacter());

    }

    @Test
    @DisplayName("기물 점수 검증")
    void checkPoint() {
        //when
        PieceType bishop = PieceType.BISHOP;

        //then

        assertEquals(bishop.getDefaultPoint(), piece.getPoint());
    }
}
