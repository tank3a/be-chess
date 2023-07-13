package chess.pieces;

import chess.controller.ChessGame;
import chess.model.Position;
import chess.model.pieces.PieceCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RookTest {

    private ChessGame chessGame;
    private Position current;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame();
        chessGame.initializeEmptyBoard();
        chessGame.placePiece("c3", PieceCreator.createWhiteRook());

        current = new Position("c3");
    }

    @Nested
    @DisplayName("룩 이동 테스트")
    class RookMoveTest {
        @Test
        @DisplayName("빈 곳으로 갈 때")
        void moveRookWhenEmpty() {
            //given
            Position after = new Position("c7");

            //when
            chessGame.move(current, after);

            //then
            assertEquals(PieceCreator.createWhiteRook(), chessGame.getPieceInPosition(after));

            //when
            current = after;
            after = new Position("f7");
            chessGame.move(current, after);

            //then
            assertEquals(PieceCreator.createWhiteRook(), chessGame.getPieceInPosition(after));
        }

        @Test
        @DisplayName("같은 색 기물이 있을 때")
        public void moveRookWhenSameColor() {
            //given
            Position south = new Position("c5");
            chessGame.placePiece("c5", PieceCreator.createWhiteBishop());

            //when, then
            assertThrows(RuntimeException.class, () -> chessGame.move(current, south));
        }

        @Test
        @DisplayName("도달한 위치에 다른 색 기물이 있을 때")
        void moveRookWhenOtherColor() {
            //given
            chessGame.placePiece("e3", PieceCreator.createBlackRook());
            Position east = new Position("e3");

            //when
            chessGame.move(current, east);

            //then
            assertEquals(PieceCreator.createWhiteRook(), chessGame.getPieceInPosition(east));
        }

        @Test
        @DisplayName("갈 수 없는 경로로 이동했을 때")
        void moveRookInvalid() {
            //given
            Position invalid = new Position("d6");

            //when, then
            assertThrows(RuntimeException.class, () -> chessGame.move(current, invalid));
        }

        @Test
        @DisplayName("가는 도중 기물이 존재할 때")
        void moveRookOverPiece() {
            //given
            Position positionToMove = new Position("c7");
            chessGame.placePiece("c5", PieceCreator.createWhiteBishop());

            //when,then
            assertThrows(RuntimeException.class, () -> chessGame.move(current, positionToMove));

        }
    }
}
