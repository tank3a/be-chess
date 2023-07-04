package chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {

    Pawn pawn;

    @Nested
    class createPawnTest {
        @Test
        @DisplayName("흰색, 검정색 Pawn 생성 테스트")
        public void create() {
            verifyPawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION);
            verifyPawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION);
        }

        @Test
        @DisplayName("Pawn 기본생성자 생성 테스트")
        public void create_기본생성자() throws Exception {
            pawn = new Pawn();
            assertEquals(Pawn.WHITE_COLOR, pawn.getColor());
        }

        private void verifyPawn(final String color, final char representation) {
            pawn = new Pawn(color);
            assertEquals(color, pawn.getColor());
            assertEquals(representation, pawn.getRepresentation());
        }

    }

}
