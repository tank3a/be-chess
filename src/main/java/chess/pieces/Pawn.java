package chess.pieces;

public class Pawn {
    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";
    public static final char WHITE_REPRESENTATION = 'p';
    public static final char BLACK_REPRESENTATION = 'P';

    private String color;
    private char colorRepresentation;

    public Pawn() {
        this.color = WHITE_COLOR;
        this.colorRepresentation = WHITE_REPRESENTATION;
    }

    public Pawn(String color) {
        this.color = color;
        this.colorRepresentation = color.equals(WHITE_COLOR) ? WHITE_REPRESENTATION : BLACK_REPRESENTATION;
    }

    public String getColor() {
        return color;
    }

    public char getRepresentation() {
        return colorRepresentation;
    }
}
