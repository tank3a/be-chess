package chess;

import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chess.StringUtils.appendNewLine;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    private Board board;

    @BeforeEach
    void init() {
        board = new Board();
        board.initialize();
    }

    @Nested
    class createTest {

        @Test
        @DisplayName("Board Initialize 테스트")
        void initialize() {
            String blankRank = appendNewLine("........");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder
                    .append(Type.ROOK.getBlackRepresentation())
                    .append(Type.KNIGHT.getBlackRepresentation())
                    .append(Type.BISHOP.getBlackRepresentation())
                    .append(Type.QUEEN.getBlackRepresentation())
                    .append(Type.KING.getBlackRepresentation())
                    .append(Type.BISHOP.getBlackRepresentation())
                    .append(Type.KNIGHT.getBlackRepresentation())
                    .append(Type.ROOK.getBlackRepresentation());
            String blackOtherRank = stringBuilder.toString();

            stringBuilder = new StringBuilder();
            stringBuilder
                    .append(Type.ROOK.getWhiteRepresentation())
                    .append(Type.KNIGHT.getWhiteRepresentation())
                    .append(Type.BISHOP.getWhiteRepresentation())
                    .append(Type.QUEEN.getWhiteRepresentation())
                    .append(Type.KING.getWhiteRepresentation())
                    .append(Type.BISHOP.getWhiteRepresentation())
                    .append(Type.KNIGHT.getWhiteRepresentation())
                    .append(Type.ROOK.getWhiteRepresentation());
            String whiteOtherRank = stringBuilder.toString();

            stringBuilder = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                stringBuilder.append(Type.PAWN.getBlackRepresentation());
            }
            String blackPawnRank = stringBuilder.toString();

            stringBuilder = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                stringBuilder.append(Type.PAWN.getWhiteRepresentation());
            }
            String whitePawnRank = stringBuilder.toString();


            assertEquals(
                    appendNewLine(blackOtherRank) +
                    appendNewLine(blackPawnRank) +
                    blankRank + blankRank + blankRank + blankRank +
                    appendNewLine(whitePawnRank) +
                    appendNewLine(whiteOtherRank),
                    board.showBoard()
            );
        }
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
            assertEquals(8, board.getPieceCount(Piece.createBlackPawn()));
            assertEquals(2, board.getPieceCount(Piece.createBlackRook()));
            assertEquals(1, board.getPieceCount(Piece.createBlackKing()));
            assertEquals(2, board.getPieceCount(Piece.createBlackKnight()));
            assertEquals(2, board.getPieceCount(Piece.createBlackBishop()));
            assertEquals(1, board.getPieceCount(Piece.createBlackQueen()));

            assertEquals(8, board.getPieceCount(Piece.createWhitePawn()));
            assertEquals(2, board.getPieceCount(Piece.createWhiteRook()));
            assertEquals(1, board.getPieceCount(Piece.createWhiteKing()));
            assertEquals(2, board.getPieceCount(Piece.createWhiteKnight()));
            assertEquals(2, board.getPieceCount(Piece.createWhiteBishop()));
            assertEquals(1, board.getPieceCount(Piece.createWhiteQueen()));

        }
    }

    @Nested
    @DisplayName("Board에서 Piece의 위치와 관련한 테스트")
    class positionTest {

        @Test
        @DisplayName("기물 반환 테스트")
        void checkPieceLocation() {
            assertEquals(Piece.createBlackRook(), board.findPiece("a8"));
            assertEquals(Piece.createBlackRook(), board.findPiece("h8"));
            assertEquals(Piece.createBlackKnight(), board.findPiece("b8"));
            assertEquals(Piece.createBlackKnight(), board.findPiece("g8"));
            assertEquals(Piece.createBlackBishop(), board.findPiece("c8"));
            assertEquals(Piece.createBlackBishop(), board.findPiece("f8"));
            assertEquals(Piece.createBlackQueen(), board.findPiece("d8"));
            assertEquals(Piece.createBlackKing(), board.findPiece("e8"));

            assertEquals(Piece.createBlackPawn(), board.findPiece("a7"));
            assertEquals(Piece.createBlackPawn(), board.findPiece("b7"));
            assertEquals(Piece.createBlackPawn(), board.findPiece("c7"));
            assertEquals(Piece.createBlackPawn(), board.findPiece("d7"));
            assertEquals(Piece.createBlackPawn(), board.findPiece("e7"));
            assertEquals(Piece.createBlackPawn(), board.findPiece("f7"));
            assertEquals(Piece.createBlackPawn(), board.findPiece("g7"));
            assertEquals(Piece.createBlackPawn(), board.findPiece("h7"));

            assertEquals(Piece.createWhiteRook(), board.findPiece("a1"));
            assertEquals(Piece.createWhiteRook(), board.findPiece("h1"));
            assertEquals(Piece.createWhiteKnight(), board.findPiece("b1"));
            assertEquals(Piece.createWhiteKnight(), board.findPiece("g1"));
            assertEquals(Piece.createWhiteBishop(), board.findPiece("c1"));
            assertEquals(Piece.createWhiteBishop(), board.findPiece("f1"));
            assertEquals(Piece.createWhiteQueen(), board.findPiece("d1"));
            assertEquals(Piece.createWhiteKing(), board.findPiece("e1"));

            assertEquals(Piece.createWhitePawn(), board.findPiece("a2"));
            assertEquals(Piece.createWhitePawn(), board.findPiece("b2"));
            assertEquals(Piece.createWhitePawn(), board.findPiece("c2"));
            assertEquals(Piece.createWhitePawn(), board.findPiece("d2"));
            assertEquals(Piece.createWhitePawn(), board.findPiece("e2"));
            assertEquals(Piece.createWhitePawn(), board.findPiece("f2"));
            assertEquals(Piece.createWhitePawn(), board.findPiece("g2"));
            assertEquals(Piece.createWhitePawn(), board.findPiece("h2"));

        }

        @Test
        @DisplayName("기물 배치 테스트")
        void placePiece() {
            //given
            String position = "b5";
            Piece piece = Piece.createBlackQueen();

            //when
            board.move(position, piece);

            //then
            assertEquals(Piece.createBlackQueen(), board.findPiece(position));
        }

        @Test
        @DisplayName("기물 이동 테스트")
        void movePiece() {
            //given
            String before = "b1";
            String after = "c3";

            //when
            board.move(before, after);

            //then
            assertEquals(Piece.createBlank(), board.findPiece(before));
            assertEquals(Piece.createWhiteKnight(), board.findPiece(after));
        }
    }

    @Nested
    @DisplayName("점수 관련 테스트")
    class pointTest {

        @Test
        @DisplayName("흑/백 점수 출력")
        void calculatePoint() {
            board.initializeEmpty();

            addPiece("b6", Piece.createBlackPawn());
            addPiece("e6", Piece.createBlackQueen());
            addPiece("b8", Piece.createBlackKing());
            addPiece("c8", Piece.createBlackRook());

            addPiece("f2", Piece.createWhitePawn());
            addPiece("g2", Piece.createWhitePawn());
            addPiece("e1", Piece.createWhiteRook());
            addPiece("f1", Piece.createWhiteKing());

            assertEquals(15.0, board.calculatePoint(Color.BLACK), 0.01);
            assertEquals(7.0, board.calculatePoint(Color.WHITE), 0.01);
        }

        @Test
        @DisplayName("Pawn 같은 라인일 때 점수 출력")
        void calculatePointWhenPawnInLine() {
            board.initializeEmpty();

            addPiece("f7", Piece.createBlackPawn());
            addPiece("f2", Piece.createWhitePawn());
            addPiece("f3", Piece.createWhitePawn());
            addPiece("e1", Piece.createWhiteRook());
            addPiece("f1", Piece.createWhiteKing());

            assertEquals(6.0, board.calculatePoint(Color.WHITE), 0.01);

        }

        private void addPiece(String position, Piece piece) {
            board.move(position, piece);
        }

        @Test
        @DisplayName("기물 점수가 높은 순으로 정렬")
        void pieceSortByScoreDescTest() {
            board.initializeEmpty();
            addPiece("b6", Piece.createBlackRook());
            addPiece("e6", Piece.createBlackQueen());
            addPiece("b8", Piece.createBlackKing());
            addPiece("c8", Piece.createBlackRook());

            addPiece("f2", Piece.createWhitePawn());
            addPiece("f3", Piece.createWhitePawn());
            addPiece("e1", Piece.createWhiteRook());
            addPiece("f1", Piece.createWhiteKing());

            assertEquals("QRRK", board.sortPieceAndPrint(Color.BLACK, true));
            assertEquals("rppk", board.sortPieceAndPrint(Color.WHITE, true));

        }

        @Test
        @DisplayName("기물 점수가 낮은 순으로 정렬")
        void pieceSortByScoreAscTest() {
            board.initializeEmpty();
            addPiece("b6", Piece.createBlackRook());
            addPiece("e6", Piece.createBlackQueen());
            addPiece("b8", Piece.createBlackKing());
            addPiece("c8", Piece.createBlackRook());

            addPiece("f2", Piece.createWhitePawn());
            addPiece("f3", Piece.createWhitePawn());
            addPiece("e1", Piece.createWhiteRook());
            addPiece("f1", Piece.createWhiteKing());

            assertEquals("KRRQ", board.sortPieceAndPrint(Color.BLACK, false));
            assertEquals("kppr", board.sortPieceAndPrint(Color.WHITE, false));
        }

    }

}
