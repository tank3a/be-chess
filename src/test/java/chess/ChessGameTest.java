package chess;

import chess.pieces.Piece;
import chess.pieces.PieceColor;
import chess.pieces.PieceCreator;
import chess.pieces.PieceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chess.StringUtils.appendNewLine;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChessGameTest {

    private ChessGame chessGame;

    @BeforeEach
    void init() {
        chessGame = new ChessGame();
        chessGame.initializeBoard();
    }

    @Nested
    class createTest {

        @Test
        @DisplayName("Board Initialize 테스트")
        void initialize() {
            String blankRank = appendNewLine("........");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder
                    .append(PieceType.ROOK.getBlackRepresentation())
                    .append(PieceType.KNIGHT.getBlackRepresentation())
                    .append(PieceType.BISHOP.getBlackRepresentation())
                    .append(PieceType.QUEEN.getBlackRepresentation())
                    .append(PieceType.KING.getBlackRepresentation())
                    .append(PieceType.BISHOP.getBlackRepresentation())
                    .append(PieceType.KNIGHT.getBlackRepresentation())
                    .append(PieceType.ROOK.getBlackRepresentation());
            String blackOtherRank = stringBuilder.toString();

            stringBuilder = new StringBuilder();
            stringBuilder
                    .append(PieceType.ROOK.getWhiteRepresentation())
                    .append(PieceType.KNIGHT.getWhiteRepresentation())
                    .append(PieceType.BISHOP.getWhiteRepresentation())
                    .append(PieceType.QUEEN.getWhiteRepresentation())
                    .append(PieceType.KING.getWhiteRepresentation())
                    .append(PieceType.BISHOP.getWhiteRepresentation())
                    .append(PieceType.KNIGHT.getWhiteRepresentation())
                    .append(PieceType.ROOK.getWhiteRepresentation());
            String whiteOtherRank = stringBuilder.toString();

            stringBuilder = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                stringBuilder.append(PieceType.PAWN.getBlackRepresentation());
            }
            String blackPawnRank = stringBuilder.toString();

            stringBuilder = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                stringBuilder.append(PieceType.PAWN.getWhiteRepresentation());
            }
            String whitePawnRank = stringBuilder.toString();


            assertEquals(
                    appendNewLine(blackOtherRank) +
                    appendNewLine(blackPawnRank) +
                    blankRank + blankRank + blankRank + blankRank +
                    appendNewLine(whitePawnRank) +
                    appendNewLine(whiteOtherRank),
                    chessGame.showBoard()
            );
        }
    }

    @Nested
    @DisplayName("이동 관련 테스트")
    class MoveTest {

        @Test
        @DisplayName("기물 이동 테스트")
        void movePiece() {
            //given
            Position before = new Position("b1");
            Position after = new Position("c3");

            //when
            chessGame.move(before, after);

            //then
            assertEquals(PieceCreator.createBlank(), chessGame.getPieceInPosition(before));
            assertEquals(PieceCreator.createWhiteKnight(), chessGame.getPieceInPosition(after));
        }

        void setPiece() {
            chessGame.initializeEmptyBoard();
            chessGame.placePiece("c4", PieceCreator.createWhiteKing());
        }

        @Test
        @DisplayName("킹 이동 테스트/다른 색 기물이 있을 때")
        void moveKingWhenOtherColor() {
            //given
            setPiece();
            chessGame.placePiece("d4", PieceCreator.createBlackRook());
            Position current = new Position("c4");
            Position east = new Position("d4");

            //when
            chessGame.move(current, east);

            //then
            assertEquals(PieceCreator.createWhiteKing(), chessGame.getPieceInPosition(east));
        }

        @Test
        @DisplayName("킹 이동 테스트/빈 곳으로 갈 때")
        void moveKingWhenEmpty() {
            //given
            setPiece();
            Position current = new Position("c4");
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
        @DisplayName("킹 이동 테스트/같은 색 기물이 있을 때")
        public void moveKingWhenSameColor() {
            //given
            setPiece();
            Position current = new Position("c4");
            Position south = new Position("c5");
            chessGame.placePiece("c5", PieceCreator.createWhiteBishop());

            //when
            chessGame.move(current, south);

            //then
            assertEquals(PieceCreator.createWhiteKing(), chessGame.getPieceInPosition(south));

        }

        @Test
        @DisplayName("퀸 이동 테스트")
        void moveQueen() {
            //given

            //when

            //then
        }
    }


    @Nested
    @DisplayName("점수 관련 테스트")
    class pointTest {

        @Test
        @DisplayName("흑/백 점수 출력")
        void calculatePoint() {
            chessGame.initializeEmptyBoard();

            addPiece("b6", PieceCreator.createBlackPawn());
            addPiece("e6", PieceCreator.createBlackQueen());
            addPiece("b8", PieceCreator.createBlackKing());
            addPiece("c8", PieceCreator.createBlackRook());

            addPiece("f2", PieceCreator.createWhitePawn());
            addPiece("g2", PieceCreator.createWhitePawn());
            addPiece("e1", PieceCreator.createWhiteRook());
            addPiece("f1", PieceCreator.createWhiteKing());

            assertEquals(15.0, chessGame.calculatePoint(PieceColor.BLACK), 0.01);
            assertEquals(7.0, chessGame.calculatePoint(PieceColor.WHITE), 0.01);
        }

        @Test
        @DisplayName("Pawn 같은 라인일 때 점수 출력")
        void calculatePointWhenPawnInLine() {
            chessGame.initializeEmptyBoard();

            addPiece("f7", PieceCreator.createBlackPawn());
            addPiece("f2", PieceCreator.createWhitePawn());
            addPiece("f3", PieceCreator.createWhitePawn());
            addPiece("e1", PieceCreator.createWhiteRook());
            addPiece("f1", PieceCreator.createWhiteKing());

            assertEquals(6.0, chessGame.calculatePoint(PieceColor.WHITE), 0.01);

        }

        private void addPiece(String input, Piece piece) {
            chessGame.placePiece(input, piece);
        }

        @Test
        @DisplayName("기물 점수가 높은 순으로 정렬")
        void pieceSortByScoreDescTest() {
            chessGame.initializeEmptyBoard();
            addPiece("b6", PieceCreator.createBlackRook());
            addPiece("e6", PieceCreator.createBlackQueen());
            addPiece("b8", PieceCreator.createBlackKing());
            addPiece("c8", PieceCreator.createBlackRook());

            addPiece("f2", PieceCreator.createWhitePawn());
            addPiece("f3", PieceCreator.createWhitePawn());
            addPiece("e1", PieceCreator.createWhiteRook());
            addPiece("f1", PieceCreator.createWhiteKing());

            assertEquals("QRRK", chessGame.sortPieceByColorAndPrint(PieceColor.BLACK, true));
            assertEquals("rppk", chessGame.sortPieceByColorAndPrint(PieceColor.WHITE, true));

        }

        @Test
        @DisplayName("기물 점수가 낮은 순으로 정렬")
        void pieceSortByScoreAscTest() {
            chessGame.initializeEmptyBoard();
            addPiece("b6", PieceCreator.createBlackRook());
            addPiece("e6", PieceCreator.createBlackQueen());
            addPiece("b8", PieceCreator.createBlackKing());
            addPiece("c8", PieceCreator.createBlackRook());

            addPiece("f2", PieceCreator.createWhitePawn());
            addPiece("f3", PieceCreator.createWhitePawn());
            addPiece("e1", PieceCreator.createWhiteRook());
            addPiece("f1", PieceCreator.createWhiteKing());

            assertEquals("KRRQ", chessGame.sortPieceByColorAndPrint(PieceColor.BLACK, false));
            assertEquals("kppr", chessGame.sortPieceByColorAndPrint(PieceColor.WHITE, false));
        }

    }
}
