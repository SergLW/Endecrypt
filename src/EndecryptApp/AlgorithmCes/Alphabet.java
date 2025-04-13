package EndecryptApp.AlgorithmCes;

import java.util.Arrays;
import java.util.List;

public class Alphabet {
    /**
     * Набор алфавитов и символов
     */
    public static final List<String> UPPER_EN = Arrays.asList(
            "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"
    );

    public static final List<String> LOWER_EN = Arrays.asList(
            "a", "b", "c", "d", "e", "f", "g",
            "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u",
            "v", "w", "x", "y", "z"
    );

    public static final List<String> UPPER_UA = Arrays.asList(
            "А", "Б", "В", "Г", "Ґ", "Д", "Е",
            "Є", "Ж", "З", "И", "І", "Ї", "Й",
            "К", "Л", "М", "Н", "О", "П", "Р",
            "С", "Т", "У", "Ф", "Х", "Ц", "Ч",
            "Ш", "Щ", "Ь", "Ю", "Я"
    );

    public static final List<String> LOWER_UA = Arrays.asList(
            "а", "б", "в", "г", "ґ", "д", "е",
            "є", "ж", "з", "и", "і", "ї", "й",
            "к", "л", "м", "н", "о", "п", "р",
            "с", "т", "у", "ф", "х", "ц", "ч",
            "ш", "щ", "ь", "ю", "я"
    );

    public static final List<String> SYMBOLS = Arrays.asList(
            ".", ",", "«", "»", "\"", "'", ":", "!", "?", " "
    );

    /**
     * Словарь для проверки запрещенных символов в названии переданного файла
     */
    public static final List<Character> INVALID_CHARS = List.of(
            '<', '>', ':', '"', '/', '\\', '|', '?', '*'
    );

    /**
     * Словари для Brute-Force
     */
    public static final List<String> TEXT_WORDS_EN = List.of(
            "the", "be", "to", "of", "and", "a", "in", "that", "have", "I",
            "it", "for", "not", "on", "with", "he", "as", "you", "do", "at",
            "this", "but", "his", "by", "from", "they", "we", "say", "her", "she",
            "or", "an", "will", "my", "one", "all", "would", "there", "their", "what",
            "so", "up", "out", "if", "about", "who", "get", "which", "go", "me"
    );

    public static final List<String> CONFIG_FILES_WORDS = List.of(
            "True", "TRUE", "FALSE", "PostProcess", "Subsystem", "Render", "Driver", "Material",
            "Steamworks", "Camera", "Texture", "bUse", "bEnable", "bCheck", "bMute", "Use", "Enable",
            "bPause", "bDiscard", "bOnScreen", "StatColorMappings", "OnlineSubsystem",
            "IdealTextureDensity", "MaxParticle", "Default", "Engine", "Scene", "Threshold",
            "Translation", "Occlusion", "Density", "Vertex", "RecDriver", "AlphaMaps"
    );

    public static final List<String> TEXT_WORDS_UA = List.of(
            "і", "в", "не", "на", "я", "бути", "це", "до", "він", "з",
            "що", "вона", "вони", "у", "ми", "ти", "той", "все", "як", "так",
            "але", "чи", "за", "від", "його", "її", "тут", "там", "ще", "уже",
            "коли", "тому", "хто", "щоб", "може", "треба", "без", "після", "перед", "між"
    );

}
