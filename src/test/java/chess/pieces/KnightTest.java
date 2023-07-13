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

public class KnightTest {

    private ChessGame chessGame;
    private Position current;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame();
        chessGame.initializeEmptyBoard();
        chessGame.placePiece("c4", PieceCreator.createWhiteKnight());

        current = new Position("c4");
    }

    @Nested
    @DisplayName("나이트 이동 테스트")
    class KnightMoveTest {
        @Test
        @DisplayName("빈 곳으로 갈 때")
        void moveKnightWhenEmpty() {
            //given
            Position west = new Position("e5");
            Position northWest = new Position("d7");

            //when
            chessGame.move(current, west);

            //then
            assertEquals(PieceCreator.createWhiteKnight(), chessGame.getPieceInPosition(west));

            //when
            chessGame.move(west, northWest);

            //then
            assertEquals(PieceCreator.createWhiteKnight(), chessGame.getPieceInPosition(northWest));
        }

        @Test
        @DisplayName("같은 색 기물이 있을 때")
        public void moveKnightWhenSameColor() {
            //given
            Position south = new Position("b2");
            chessGame.placePiece("b2", PieceCreator.createWhiteBishop());

            //when, then
            assertThrows(RuntimeException.class, () -> chessGame.move(current, south));
        }

        @Test
        @DisplayName("다른 색 기물이 있을 때")
        void moveKnightWhenOtherColor() {
            //given
            chessGame.placePiece("d6", PieceCreator.createBlackRook());
            Position east = new Position("d6");

            //when
            chessGame.move(current, east);

            //then
            assertEquals(PieceCreator.createWhiteKnight(), chessGame.getPieceInPosition(east));
        }

        @Test
        @DisplayName("갈 수 없는 경로로 이동했을 때")
        void moveKnightInvalid() {
            //given
            Position invalid = new Position("e8");

            //when, then
            assertThrows(RuntimeException.class, () -> chessGame.move(current, invalid));
        }
    }

}
