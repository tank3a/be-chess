package chess;

import chess.pieces.Piece;
import chess.pieces.PieceColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChessGameTest {

    private ChessGame chessGame;

    @BeforeEach
    void init() {
        chessGame = new ChessGame();
        chessGame.initializeBoard();
    }

    @Test
    @DisplayName("기물 이동 테스트")
    void movePiece() {
        //given
        Position before = new Position("b1");
        Position after = new Position("c3");

        //when
        chessGame.move(before, after);

        //then
        assertEquals(Piece.createBlank(), chessGame.getPieceInPosition(before));
        assertEquals(Piece.createWhiteKnight(), chessGame.getPieceInPosition(after));
    }

    @Nested
    @DisplayName("점수 관련 테스트")
    class pointTest {

        @Test
        @DisplayName("흑/백 점수 출력")
        void calculatePoint() {
            chessGame.initializeEmptyBoard();

            addPiece("b6", Piece.createBlackPawn());
            addPiece("e6", Piece.createBlackQueen());
            addPiece("b8", Piece.createBlackKing());
            addPiece("c8", Piece.createBlackRook());

            addPiece("f2", Piece.createWhitePawn());
            addPiece("g2", Piece.createWhitePawn());
            addPiece("e1", Piece.createWhiteRook());
            addPiece("f1", Piece.createWhiteKing());

            assertEquals(15.0, chessGame.calculatePoint(PieceColor.BLACK), 0.01);
            assertEquals(7.0, chessGame.calculatePoint(PieceColor.WHITE), 0.01);
        }

        @Test
        @DisplayName("Pawn 같은 라인일 때 점수 출력")
        void calculatePointWhenPawnInLine() {
            chessGame.initializeEmptyBoard();

            addPiece("f7", Piece.createBlackPawn());
            addPiece("f2", Piece.createWhitePawn());
            addPiece("f3", Piece.createWhitePawn());
            addPiece("e1", Piece.createWhiteRook());
            addPiece("f1", Piece.createWhiteKing());

            assertEquals(6.0, chessGame.calculatePoint(PieceColor.WHITE), 0.01);

        }

        private void addPiece(String input, Piece piece) {
            chessGame.placePiece(input, piece);
        }

        @Test
        @DisplayName("기물 점수가 높은 순으로 정렬")
        void pieceSortByScoreDescTest() {
            chessGame.initializeEmptyBoard();
            addPiece("b6", Piece.createBlackRook());
            addPiece("e6", Piece.createBlackQueen());
            addPiece("b8", Piece.createBlackKing());
            addPiece("c8", Piece.createBlackRook());

            addPiece("f2", Piece.createWhitePawn());
            addPiece("f3", Piece.createWhitePawn());
            addPiece("e1", Piece.createWhiteRook());
            addPiece("f1", Piece.createWhiteKing());

            assertEquals("QRRK", chessGame.sortPieceByColorAndPrint(PieceColor.BLACK, true));
            assertEquals("rppk", chessGame.sortPieceByColorAndPrint(PieceColor.WHITE, true));

        }

        @Test
        @DisplayName("기물 점수가 낮은 순으로 정렬")
        void pieceSortByScoreAscTest() {
            chessGame.initializeEmptyBoard();
            addPiece("b6", Piece.createBlackRook());
            addPiece("e6", Piece.createBlackQueen());
            addPiece("b8", Piece.createBlackKing());
            addPiece("c8", Piece.createBlackRook());

            addPiece("f2", Piece.createWhitePawn());
            addPiece("f3", Piece.createWhitePawn());
            addPiece("e1", Piece.createWhiteRook());
            addPiece("f1", Piece.createWhiteKing());

            assertEquals("KRRQ", chessGame.sortPieceByColorAndPrint(PieceColor.BLACK, false));
            assertEquals("kppr", chessGame.sortPieceByColorAndPrint(PieceColor.WHITE, false));
        }

    }
}
