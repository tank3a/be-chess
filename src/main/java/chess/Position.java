package chess;

public class Position {

    private final int FILE;
    private final int RANK;

    public Position(String position) {
        FILE = position.charAt(0) - 'a';
        RANK = 8 - Character.getNumericValue(position.charAt(1));
    }

    public int getFile() {
        return FILE;
    }

    public int getRank() {
        return RANK;
    }


}
