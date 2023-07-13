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

public class PawnTest {

    private ChessGame chessGame;
    private Position blackCurrent;
    private Position whiteCurrent;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame();
        chessGame.initializeEmptyBoard();
        chessGame.placePiece("c2", PieceCreator.createWhitePawn());
        chessGame.placePiece("c7", PieceCreator.createBlackPawn());

        whiteCurrent = new Position("c2");
        blackCurrent = new Position("c7");
    }

    @Nested
    @DisplayName("폰 이동 테스트")
    class PawnMoveTest {

        @Test
        @DisplayName("대각선으로 이동하려고 할 때 대각선에 기물이 존재하는 경우")
        public void moveDiagonalWhenPieceExist() {
            //given
            Position afterWhite = new Position("d3");
            Position afterBlackFail = new Position("b6");
            chessGame.placePiece("d3", PieceCreator.createBlackBishop());
            chessGame.placePiece("b6", PieceCreator.createBlackBishop());


            //when
            chessGame.move(whiteCurrent, afterWhite);

            //then
            assertEquals(PieceCreator.createWhitePawn(), chessGame.getPieceInPosition(afterWhite));
            assertThrows(RuntimeException.class, () -> chessGame.move(whiteCurrent, afterBlackFail));
        }

        @Test
        @DisplayName("대각선으로 이동하려고 할 때 대각선에 기물이 존재하지 않는 경우")
        public void moveDiagonalWhenPieceNotExist() {
            //given
            Position afterWhiteFail = new Position("d3");
            Position afterBlackFail = new Position("b6");
            chessGame.placePiece("c3", PieceCreator.createBlackBishop());
            chessGame.placePiece("c6", PieceCreator.createBlackBishop());


            //when, then
            assertThrows(RuntimeException.class, () -> chessGame.move(whiteCurrent, afterWhiteFail));
            assertThrows(RuntimeException.class, () -> chessGame.move(blackCurrent, afterBlackFail));
        }

        @Test
        @DisplayName("전진할 때 기물이 존재하지 않는 경우")
        public void movePawnFrontWhenNotExist() {
            //given
            Position afterWhite = new Position("c3");
            Position afterBlack = new Position("c6");

            //when
            chessGame.move(blackCurrent, afterBlack);
            chessGame.move(whiteCurrent, afterWhite);

            //then
            assertEquals(PieceCreator.createWhitePawn(), chessGame.getPieceInPosition(afterWhite));
            assertEquals(PieceCreator.createBlackPawn(), chessGame.getPieceInPosition(afterBlack));
        }

        @Test
        @DisplayName("전진할 때 기물이 존재하는 경우")
        public void movePawnFrontWhenExist() {
            //given
            Position afterWhiteFail = new Position("c3");
            Position afterBlackFail = new Position("c6");
            chessGame.placePiece("c3", PieceCreator.createBlackBishop());
            chessGame.placePiece("c6", PieceCreator.createBlackBishop());

            //when, then
            assertThrows(RuntimeException.class, () -> chessGame.move(whiteCurrent, afterWhiteFail));
            assertThrows(RuntimeException.class, () -> chessGame.move(blackCurrent, afterBlackFail));
        }

        @Test
        @DisplayName("갈 수 없는 경로로 이동했을 때")
        void movePawnInvalid() {
            //given
            Position invalid = new Position("d6");

            //when, then
            assertThrows(RuntimeException.class, () -> chessGame.move(whiteCurrent, invalid));
        }
    }

}
