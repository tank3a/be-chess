package chess;

import chess.model.pieces.Piece;
import chess.model.pieces.PieceCreator;
import chess.view.BoardView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class BoardViewTest {

    private BoardView boardView;

    @BeforeEach
    void setUp() {
        boardView = new BoardView();
    }

    @Nested
    @DisplayName("정렬 테스트")
    class sortTest {

        @Test
        @DisplayName("색깔별 오름차순 정렬 테스트")
        public void sortPieceAsc() {
            //given
            List<Piece> whitePieceList = new ArrayList<>();
            whitePieceList.add(PieceCreator.createWhiteBishop());
            whitePieceList.add(PieceCreator.createWhiteBishop());
            whitePieceList.add(PieceCreator.createWhiteQueen());
            whitePieceList.add(PieceCreator.createWhitePawn());
            whitePieceList.add(PieceCreator.createWhiteRook());
            whitePieceList.add(PieceCreator.createWhiteKing());

            List<Piece> blackPieceList = new ArrayList<>();
            blackPieceList.add(PieceCreator.createBlackBishop());
            blackPieceList.add(PieceCreator.createBlackQueen());
            blackPieceList.add(PieceCreator.createBlackBishop());
            blackPieceList.add(PieceCreator.createBlackPawn());
            blackPieceList.add(PieceCreator.createBlackRook());
            blackPieceList.add(PieceCreator.createBlackKing());

            //when


            //then
            assertEquals("kpbbrq", boardView.printPieceAsc(whitePieceList));
            assertEquals("KPBBRQ", boardView.printPieceAsc(blackPieceList));
        }
    }
}
