package chess;

public class StringUtils {

    public static final String NEWLINE = System.getProperty("line.separator");

    private StringUtils() {}

    public static String appendNewLine(String input) {
        return input + NEWLINE;
    }
}
