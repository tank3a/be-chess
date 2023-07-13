package chess.model.board;

import chess.controller.Direction;
import chess.exception.InvalidMoveException;
import chess.model.Position;
import chess.model.pieces.PieceColor;
import chess.model.pieces.PieceCreator;
import chess.model.pieces.PieceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static chess.StringUtils.appendNewLine;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Nested
    @DisplayName("생성 테스트")
    class createTest {

        @Test
        @DisplayName("보드 기물을 채운 초기화 테스트")
        void initialize() {
            board.initialize();

//            String blankRank = "........";
//            String blackOtherRank = "RNBQKBNR";
//            String blackPawnRank = "PPPPPPPP";
//            String whitePawnRank = "pppppppp";
//            String whiteOtherRank = "rnbqkbnr";


            assertEquals(new Rank(8), board.getRank(0));
            assertEquals(new Rank(7), board.getRank(1));
            assertEquals(new Rank(6), board.getRank(2));
            assertEquals(new Rank(5), board.getRank(3));
            assertEquals(new Rank(4), board.getRank(4));
            assertEquals(new Rank(3), board.getRank(5));
            assertEquals(new Rank(2), board.getRank(6));
            assertEquals(new Rank(1), board.getRank(7));

        }

        @Test
        @DisplayName("보드 기물을 채우지 않은 초기화 테스트")
        void initializeEmpty() {
            board.initializeEmpty();

            Rank blankRank = new Rank();

            assertEquals(blankRank, board.getRank(0));
            assertEquals(blankRank, board.getRank(1));
            assertEquals(blankRank, board.getRank(2));
            assertEquals(blankRank, board.getRank(3));
            assertEquals(blankRank, board.getRank(4));
            assertEquals(blankRank, board.getRank(5));
            assertEquals(blankRank, board.getRank(6));
            assertEquals(blankRank, board.getRank(7));

        }

        @Test
        @DisplayName("보드가 초기화 되었는지 검증")
        void isInitialized() {
            board.initialize();

            assertTrue(board.isInitialized());

            board.initializeEmpty();

            assertTrue(board.isInitialized());
        }
    }

    @Nested
    @DisplayName("기물 count 관련 테스트")
    class countTest {

        @Test
        void countAll() {
            board.initialize();

            assertEquals(32, board.countAll());

            board.initializeEmpty();

            assertEquals(0, board.countAll());
        }

        @Test
        void getPieceCount() {
            board.initialize();

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
    @DisplayName("기물 위치 관련 테스트")
    class searchTest {

        @Test
        void findPiece() {
            board.initialize();

            assertEquals(PieceCreator.createBlackRook(), board.findPiece("a8"));
            assertEquals(PieceCreator.createBlackRook(), board.findPiece("h8"));
            assertEquals(PieceCreator.createBlackKnight(), board.findPiece("b8"));
            assertEquals(PieceCreator.createBlackKnight(), board.findPiece(new Position("g8")));
            assertEquals(PieceCreator.createBlackBishop(), board.findPiece(new Position("c8")));
            assertEquals(PieceCreator.createBlackBishop(), board.findPiece(new Position("f8")));
            assertEquals(PieceCreator.createBlackQueen(), board.findPiece(new Position("d8")));
            assertEquals(PieceCreator.createBlackKing(), board.findPiece("e8"));

            assertEquals(PieceCreator.createBlackPawn(), board.findPiece(new Position("a7")));
            assertEquals(PieceCreator.createBlackPawn(), board.findPiece(new Position("b7")));
            assertEquals(PieceCreator.createBlackPawn(), board.findPiece(new Position("c7")));
            assertEquals(PieceCreator.createBlackPawn(), board.findPiece("d7"));
            assertEquals(PieceCreator.createBlackPawn(), board.findPiece("e7"));
            assertEquals(PieceCreator.createBlackPawn(), board.findPiece("f7"));
            assertEquals(PieceCreator.createBlackPawn(), board.findPiece("g7"));
            assertEquals(PieceCreator.createBlackPawn(), board.findPiece(new Position("h7")));

            assertEquals(PieceCreator.createWhiteRook(), board.findPiece("a1"));
            assertEquals(PieceCreator.createWhiteRook(), board.findPiece("h1"));
            assertEquals(PieceCreator.createWhiteKnight(), board.findPiece("b1"));
            assertEquals(PieceCreator.createWhiteKnight(), board.findPiece(new Position("g1")));
            assertEquals(PieceCreator.createWhiteBishop(), board.findPiece(new Position("c1")));
            assertEquals(PieceCreator.createWhiteBishop(), board.findPiece(new Position("f1")));
            assertEquals(PieceCreator.createWhiteQueen(), board.findPiece(new Position("d1")));
            assertEquals(PieceCreator.createWhiteKing(), board.findPiece("e1"));

            assertEquals(PieceCreator.createWhitePawn(), board.findPiece(new Position("a2")));
            assertEquals(PieceCreator.createWhitePawn(), board.findPiece(new Position("b2")));
            assertEquals(PieceCreator.createWhitePawn(), board.findPiece(new Position("c2")));
            assertEquals(PieceCreator.createWhitePawn(), board.findPiece("d2"));
            assertEquals(PieceCreator.createWhitePawn(), board.findPiece("e2"));
            assertEquals(PieceCreator.createWhitePawn(), board.findPiece("f2"));
            assertEquals(PieceCreator.createWhitePawn(), board.findPiece("g2"));
            assertEquals(PieceCreator.createWhitePawn(), board.findPiece(new Position("h2")));
        }

        @Test
        void setPiece() {
            board.initializeEmpty();

            board.setPiece(new Position("c4"), PieceCreator.createBlackBishop());
            board.setPiece(new Position("a8"), PieceCreator.createWhiteRook());

            assertEquals(PieceCreator.createBlackBishop(), board.findPiece("c4"));
            assertEquals(PieceCreator.createWhiteRook(), board.findPiece("a8"));
        }

        @Test
        @DisplayName("기물 사이에 존재 시 예외처리 테스트")
        void existPieceBetween() {
            board.initializeEmpty();

            Position start = new Position("c4");

            board.setPiece(start, PieceCreator.createBlackBishop());
            board.setPiece(new Position("c7"), PieceCreator.createBlackPawn());
            board.setPiece(new Position("e4"), PieceCreator.createWhiteRook());
            board.setPiece(new Position("b3"), PieceCreator.createWhiteRook());

            Direction north = Direction.NORTH;
            Direction east = Direction.EAST;
            Direction sw = Direction.SOUTHWEST;

            assertDoesNotThrow(() -> board.existPieceBetween(start, new Position("c7"), north));
            assertDoesNotThrow(() -> board.existPieceBetween(start, new Position("e4"), east));
            assertDoesNotThrow(() -> board.existPieceBetween(start, new Position("b3"), sw));


            assertThrows(InvalidMoveException.class, () -> board.existPieceBetween(start, new Position("c8"), north));
            assertThrows(InvalidMoveException.class, () -> board.existPieceBetween(start, new Position("g4"), east));
            assertThrows(InvalidMoveException.class, () -> board.existPieceBetween(start, new Position("a2"), sw));

        }
    }

    @Test
    @DisplayName("점수 계산 테스트")
    void calculatePoint() {
        board.initializeEmpty();

        board.setPiece(new Position("c4"), PieceCreator.createBlackBishop());
        board.setPiece(new Position("c7"), PieceCreator.createBlackPawn());
        board.setPiece(new Position("c5"), PieceCreator.createBlackPawn());
        board.setPiece(new Position("e4"), PieceCreator.createWhiteRook());
        board.setPiece(new Position("b3"), PieceCreator.createWhiteRook());
        board.setPiece(new Position("b5"), PieceCreator.createWhitePawn());

        assertEquals(4, board.calculatePoint(PieceColor.BLACK));
        assertEquals(11, board.calculatePoint(PieceColor.WHITE));
    }

}