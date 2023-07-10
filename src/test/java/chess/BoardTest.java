package chess;

import chess.pieces.Piece;
import chess.pieces.PieceCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    private Board board;

    @BeforeEach
    void init() {
        board = new Board();
        board.initialize();
    }

    @Nested
    @DisplayName("Board의 Piece를 count하는 테스트")
    class countTest {

        @Test
        @DisplayName("초기 기물 개수 : 32개")
        void count() {
            assertEquals(32, board.countAll());
        }

        @Test
        @DisplayName("특정 기물 개수")
        void countSpecific() {
            assertEquals(8, board.getPieceCount(PieceCreator.createBlackPawn()));
            assertEquals(2, board.getPieceCount(PieceCreator.createBlackRook()));
            assertEquals(1, board.getPieceCount(PieceCreator.createBlackKing()));
            assertEquals(2, board.getPieceCount(PieceCreator.createBlackKnight()));
            assertEquals(2, board.getPieceCount(PieceCreator.createBlackBishop()));
            assertEquals(1, board.getPieceCount(PieceCreator.createBlackQueen()));

            assertEquals(8, board.getPieceCount(PieceCreator.createWhitePawn()));
            assertEquals(2, board.getPieceCount(PieceCreator.createWhiteRook()));
            assertEquals(1, board.getPieceCount(PieceCreator.createWhiteKing()));
            assertEquals(2, board.getPieceCount(PieceCreator.createWhiteKnight()));
            assertEquals(2, board.getPieceCount(PieceCreator.createWhiteBishop()));
            assertEquals(1, board.getPieceCount(PieceCreator.createWhiteQueen()));

        }
    }

    @Nested
    @DisplayName("Board에서 Piece의 위치와 관련한 테스트")
    class positionTest {

        @Test
        @DisplayName("기물 반환 테스트")
        void checkPieceLocation() {
            assertEquals(PieceCreator.createBlackRook(), board.findPiece("a8"));
            assertEquals(PieceCreator.createBlackRook(), board.findPiece("h8"));
            assertEquals(PieceCreator.createBlackKnight(), board.findPiece("b8"));
            assertEquals(PieceCreator.createBlackKnight(), board.findPiece("g8"));
            assertEquals(PieceCreator.createBlackBishop(), board.findPiece("c8"));
            assertEquals(PieceCreator.createBlackBishop(), board.findPiece("f8"));
            assertEquals(PieceCreator.createBlackQueen(), board.findPiece("d8"));
            assertEquals(PieceCreator.createBlackKing(), board.findPiece("e8"));

            assertEquals(PieceCreator.createBlackPawn(), board.findPiece("a7"));
            assertEquals(PieceCreator.createBlackPawn(), board.findPiece("b7"));
            assertEquals(PieceCreator.createBlackPawn(), board.findPiece("c7"));
            assertEquals(PieceCreator.createBlackPawn(), board.findPiece("d7"));
            assertEquals(PieceCreator.createBlackPawn(), board.findPiece("e7"));
            assertEquals(PieceCreator.createBlackPawn(), board.findPiece("f7"));
            assertEquals(PieceCreator.createBlackPawn(), board.findPiece("g7"));
            assertEquals(PieceCreator.createBlackPawn(), board.findPiece("h7"));

            assertEquals(PieceCreator.createWhiteRook(), board.findPiece("a1"));
            assertEquals(PieceCreator.createWhiteRook(), board.findPiece("h1"));
            assertEquals(PieceCreator.createWhiteKnight(), board.findPiece("b1"));
            assertEquals(PieceCreator.createWhiteKnight(), board.findPiece("g1"));
            assertEquals(PieceCreator.createWhiteBishop(), board.findPiece("c1"));
            assertEquals(PieceCreator.createWhiteBishop(), board.findPiece("f1"));
            assertEquals(PieceCreator.createWhiteQueen(), board.findPiece("d1"));
            assertEquals(PieceCreator.createWhiteKing(), board.findPiece("e1"));

            assertEquals(PieceCreator.createWhitePawn(), board.findPiece("a2"));
            assertEquals(PieceCreator.createWhitePawn(), board.findPiece("b2"));
            assertEquals(PieceCreator.createWhitePawn(), board.findPiece("c2"));
            assertEquals(PieceCreator.createWhitePawn(), board.findPiece("d2"));
            assertEquals(PieceCreator.createWhitePawn(), board.findPiece("e2"));
            assertEquals(PieceCreator.createWhitePawn(), board.findPiece("f2"));
            assertEquals(PieceCreator.createWhitePawn(), board.findPiece("g2"));
            assertEquals(PieceCreator.createWhitePawn(), board.findPiece("h2"));

        }

        @Test
        @DisplayName("기물 배치 테스트")
        void placePiece() {
            //given
            Position position = new Position("b5");
            Piece piece = PieceCreator.createBlackQueen();

            //when
            board.setPiece(position, piece);

            //then
            assertEquals(PieceCreator.createBlackQueen(), board.findPiece(position));
        }
    }

}
