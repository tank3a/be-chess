package chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {

    Pawn pawn;

    @Test
    @DisplayName("흰색 폰이 생성되어야 한다.")
    public void create() {

        verifyPawn(Pawn.WHITE_COLOR);
        verifyPawn(Pawn.BLACK_COLOR);
    }

    @Test
    public void create_기본생성자() throws Exception {
        pawn = new Pawn();
        assertEquals(Pawn.WHITE_COLOR, pawn.getColor());
    }


    private void verifyPawn(final String color) {
        pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }


}
