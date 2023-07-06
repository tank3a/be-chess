package chess;

public class Position {

    //입력을 좌표로 변환
    public static int convertXIndex(String position) {
        return position.charAt(0) - 'a';
    }

    public static int convertYIndex(String position) {
        return 8 - Character.getNumericValue(position.charAt(1));

    }

}
