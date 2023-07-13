package chess;

import chess.controller.ChessGame;
import chess.model.pieces.Piece;
import chess.model.pieces.PieceColor;
import chess.model.pieces.PieceCreator;
import chess.model.pieces.PieceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chess.StringUtils.appendNewLine;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChessGameTest {

    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
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


//            assertEquals(
//                    appendNewLine(blackOtherRank) +
//                    appendNewLine(blackPawnRank) +
//                    blankRank + blankRank + blankRank + blankRank +
//                    appendNewLine(whitePawnRank) +
//                    appendNewLine(whiteOtherRank),
//                    chessGame.showBoard()
//            );
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
