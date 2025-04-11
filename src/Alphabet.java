import java.util.Arrays;
import java.util.List;

public class Alphabet {
    /**
     * Набор алфавитов и символов
     */
    public static final List<String> UPPER = Arrays.asList(
            "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"
    );

    public static final List<String> LOWER = Arrays.asList(
            "a", "b", "c", "d", "e", "f", "g",
            "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u",
            "v", "w", "x", "y", "z"
    );

    public static final List<String> SYMBOLS = Arrays.asList(
            ".", ",", "«", "»", "\"", "'", ":", "!", "?", " "
    );
    public static final List<Character> INVALID_CHARS = List.of(
            '<', '>', ':', '"', '/', '\\', '|', '?', '*'
    );





}
