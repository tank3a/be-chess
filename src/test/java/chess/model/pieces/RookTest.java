package chess.model.pieces;

import chess.controller.ChessGame;
import chess.controller.Direction;
import chess.exception.InvalidMoveException;
import chess.exception.PieceNotExistException;
import chess.model.Position;
import chess.model.board.Board;
import chess.model.pieces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class RookTest {

    private Piece piece;
    private Position current;

    @BeforeEach
    void setUp() {
        piece = PieceCreator.createWhiteRook();
        current = new Position(4, 4);
    }


    @Test
    @DisplayName("룩 이동 검증")
    void moveRookLinear() {
        //given
        Position after1 = new Position(7, 4);
        Position after2 = new Position(4, 1);
        Position after3 = new Position(6, 6);
        Position after4 = new Position(5, 7);

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
        Piece piece2 = PieceCreator.createBlackRook();
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
        Piece piece2 = PieceCreator.createBlackRook();

        PieceType rook = PieceType.ROOK;

        assertTrue(piece.compareType(rook));
        assertFalse(piece.isPawn());
        assertEquals(rook.getWhiteRepresentation(), piece.getTypeInCharacter());
        assertEquals(rook.getBlackRepresentation(), piece2.getTypeInCharacter());

    }

    @Test
    @DisplayName("기물 점수 검증")
    void checkPoint() {
        //when
        PieceType rook = PieceType.ROOK;

        //then

        assertEquals(rook.getDefaultPoint(), piece.getPoint());
    }
}
