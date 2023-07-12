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

public class KingTest {

    private ChessGame chessGame;
    private Position current;

    @BeforeEach
    void init() {
        chessGame = new ChessGame();
        chessGame.initializeEmptyBoard();
        chessGame.placePiece("c4", PieceCreator.createWhiteKing());

        current = new Position("c4");
    }

    @Nested
    @DisplayName("킹 이동 테스트")
    class KingMoveTest {
        @Test
        @DisplayName("빈 곳으로 갈 때")
        void moveKingWhenEmpty() {
            //given
            Position west = new Position("b4");
            Position northWest = new Position("a5");

            //when
            chessGame.move(current, west);

            //then
            assertEquals(PieceCreator.createWhiteKing(), chessGame.getPieceInPosition(west));

            //when
            chessGame.move(west, northWest);

            //then
            assertEquals(PieceCreator.createWhiteKing(), chessGame.getPieceInPosition(northWest));
        }

        @Test
        @DisplayName("같은 색 기물이 있을 때")
        public void moveKingWhenSameColor() {
            //given
            Position south = new Position("c5");
            chessGame.placePiece("c5", PieceCreator.createWhiteBishop());

            //when, then
            assertThrows(RuntimeException.class, () -> chessGame.move(current, south));
        }

        @Test
        @DisplayName("다른 색 기물이 있을 때")
        void moveKingWhenOtherColor() {
            //given
            chessGame.placePiece("d4", PieceCreator.createBlackRook());
            Position east = new Position("d4");

            //when
            chessGame.move(current, east);

            //then
            assertEquals(PieceCreator.createWhiteKing(), chessGame.getPieceInPosition(east));
        }

        @Test
        @DisplayName("갈 수 없는 경로로 이동했을 때")
        void moveKingInvalid() {
            //given
            Position invalid = new Position("d6");

            //when, then
            assertThrows(RuntimeException.class, () -> chessGame.move(current, invalid));
        }
    }

}
