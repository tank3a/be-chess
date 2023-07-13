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

public class BlankPieceTest {

    private Piece piece;
    private Position current;

    @BeforeEach
    void setUp() {
        piece = PieceCreator.createBlank();
        current = new Position(4, 4);
    }


    @Test
    @DisplayName("빈 칸 이동 오류 검증")
    void moveBlank() {
        //given
        Position after1 = new Position(3, 4);
        Position after2 = new Position(3, 6);

        //when, then
        assertThrows(PieceNotExistException.class, () -> piece.verifyMovePosition(current, after1));
        assertThrows(PieceNotExistException.class, () -> piece.verifyMovePosition(current, after2));
    }

    @Test
    @DisplayName("색깔 검증")
    void checkColor() {
        //given
        PieceColor black = PieceColor.BLACK;
        PieceColor white = PieceColor.WHITE;

        //then
        assertFalse(piece.isWhite());
        assertFalse(piece.isBlack());
        assertFalse(piece.compareColor(white));
        assertFalse(piece.compareColor(black));
    }

    @Test
    @DisplayName("기물 타입 검증")
    void checkType() {
        //given
        PieceType noPiece = PieceType.NO_PIECE;

        assertTrue(piece.compareType(noPiece));
        assertFalse(piece.isPawn());
        assertEquals(noPiece.getWhiteRepresentation(), piece.getTypeInCharacter());
        assertEquals(noPiece.getBlackRepresentation(), piece.getTypeInCharacter());
    }

    @Test
    @DisplayName("기물 점수 검증")
    void checkPoint() {
        //when
        PieceType king = PieceType.KING;

        //then

        assertEquals(king.getDefaultPoint(), piece.getPoint());
    }
}
