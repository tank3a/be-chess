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

public class QueenTest {

    private Piece piece;
    private Position current;

    @BeforeEach
    void setUp() {
        piece = PieceCreator.createWhiteQueen();
        current = new Position(4, 4);
    }


    @Test
    @DisplayName("퀸 이동 검증")
    void moveQueen() {
        //given
        Position after1 = new Position(7, 7);
        Position after2 = new Position(2, 6);
        Position after3 = new Position(2, 4);
        Position after4 = new Position(4, 6);
        Position after5 = new Position(3, 6);
        Position after6 = new Position(6, 7);

        //when, then
        assertDoesNotThrow(() -> piece.verifyMovePosition(current, after1));
        assertDoesNotThrow(() -> piece.verifyMovePosition(current, after2));
        assertDoesNotThrow(() -> piece.verifyMovePosition(current, after3));
        assertDoesNotThrow(() -> piece.verifyMovePosition(current, after4));
        assertThrows(InvalidMoveException.class, () -> piece.verifyMovePosition(current, after5));
        assertThrows(InvalidMoveException.class, () -> piece.verifyMovePosition(current, after6));
    }

    @Test
    @DisplayName("색깔 검증")
    void checkColor() {
        //given
        Piece piece2 = PieceCreator.createBlackQueen();
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
        Piece piece2 = PieceCreator.createBlackQueen();

        PieceType queen = PieceType.QUEEN;

        assertTrue(piece.compareType(queen));
        assertFalse(piece.isPawn());
        assertEquals(queen.getWhiteRepresentation(), piece.getTypeInCharacter());
        assertEquals(queen.getBlackRepresentation(), piece2.getTypeInCharacter());

    }

    @Test
    @DisplayName("기물 점수 검증")
    void checkPoint() {
        //when
        PieceType queen = PieceType.QUEEN;

        //then

        assertEquals(queen.getDefaultPoint(), piece.getPoint());
    }
}
