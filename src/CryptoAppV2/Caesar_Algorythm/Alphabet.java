package CryptoAppV2.Caesar_Algorythm;

import java.util.List;

public class Alphabet {
    /**
     *  All Alphabets
     */
    public static final String UPPER_EN = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWER_EN = "abcdefghijklmnopqrstuvwxyz";
    public static final String UPPER_UA = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ";
    public static final String LOWER_UA = "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя";
    public static final String SYMBOLS = ".,«»\"':!? ";
    public static final String DIGITS = "0123456789";

    public static final List<String> ALL_ALPHABETS = List.of(UPPER_EN, LOWER_EN, UPPER_UA, LOWER_UA, SYMBOLS, DIGITS);

    /**
     * Words for Brute-Force
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
