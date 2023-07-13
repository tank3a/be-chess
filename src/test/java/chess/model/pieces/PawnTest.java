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

public class PawnTest {

    private Piece whitePawn;
    private Piece blackPawn;
    private Position current;

    @BeforeEach
    void setUp() {
        whitePawn = PieceCreator.createWhitePawn();
        blackPawn = PieceCreator.createBlackPawn();
        current = new Position(4, 4);
    }


    @Test
    @DisplayName("흰색 폰 이동 검증")
    void moveWhitePawn() {
        //given
        Position after1 = new Position(4, 3);
        Position after2 = new Position(3, 3);
        Position after3 = new Position(5, 4);
        Position after4 = new Position(4, 5);

        //when, then
        assertDoesNotThrow(() -> whitePawn.verifyMovePosition(current, after1));
        assertDoesNotThrow(() -> whitePawn.verifyMovePosition(current, after2));
        assertThrows(InvalidMoveException.class, () -> whitePawn.verifyMovePosition(current, after3));
        assertThrows(InvalidMoveException.class, () -> whitePawn.verifyMovePosition(current, after4));
    }

    @Test
    @DisplayName("검정 폰 이동 검증")
    void moveBlackPawn() {
        //given
        Position after1 = new Position(4, 5);
        Position after2 = new Position(3, 5);
        Position after3 = new Position(5, 4);
        Position after4 = new Position(4, 3);

        //when, then
        assertDoesNotThrow(() -> blackPawn.verifyMovePosition(current, after1));
        assertDoesNotThrow(() -> blackPawn.verifyMovePosition(current, after2));
        assertThrows(InvalidMoveException.class, () -> blackPawn.verifyMovePosition(current, after3));
        assertThrows(InvalidMoveException.class, () -> blackPawn.verifyMovePosition(current, after4));
    }

    @Test
    @DisplayName("색깔 검증")
    void checkColor() {
        //given
        Piece noPiece = PieceCreator.createBlank();

        //when
        PieceColor black = PieceColor.BLACK;
        PieceColor white = PieceColor.WHITE;

        //then
        assertTrue(whitePawn.isWhite());
        assertTrue(whitePawn.compareColor(white));
        assertTrue(blackPawn.isBlack());
        assertTrue(blackPawn.compareColor(black));
        assertTrue(whitePawn.isOppositeColor(blackPawn));
        assertThrows(PieceNotExistException.class, () -> whitePawn.isOppositeColor(noPiece));
        assertThrows(PieceNotExistException.class, () -> blackPawn.isOppositeColor(noPiece));

    }

    @Test
    @DisplayName("기물 타입 검증")
    void checkType() {
        //given
        PieceType pawn = PieceType.PAWN;

        assertTrue(whitePawn.compareType(pawn));
        assertTrue(whitePawn.isPawn());
        assertTrue(blackPawn.compareType(pawn));
        assertTrue(blackPawn.isPawn());
        assertEquals(pawn.getWhiteRepresentation(), whitePawn.getTypeInCharacter());
        assertEquals(pawn.getBlackRepresentation(), blackPawn.getTypeInCharacter());

    }

    @Test
    @DisplayName("기물 점수 검증")
    void checkPoint() {
        //when
        PieceType pawn = PieceType.PAWN;

        //then

        assertEquals(pawn.getDefaultPoint(), whitePawn.getPoint());
        assertEquals(pawn.getDefaultPoint(), blackPawn.getPoint());

    }
}
