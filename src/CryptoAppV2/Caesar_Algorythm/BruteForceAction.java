package CryptoAppV2.Caesar_Algorythm;

import java.util.List;

import static CryptoAppV2.Caesar_Algorythm.Alphabet.*;

public class BruteForceAction extends CipherAlgorithm {
    private static int lastBestKey = -1;

    public static int getLastBestKey() {
        return lastBestKey;
    }

    @Override
    public String execute(String content) {
        int maxCount = 0;
        int bestKey = 0;
        String bestDecryptedContent = content;
        int maxLength = maxLengthAlphabet(content); // Find the length of the language used in the text
        for (int key = maxLength - 1; key >= 0; key--) {
            String testDecryptedContent = algorithm(content, -key);
            int count = countTextWords(testDecryptedContent);
            if (count > maxCount) {
                maxCount = count;
                bestDecryptedContent = testDecryptedContent;
                bestKey = key;
            }
        }
        lastBestKey = bestKey;
        return bestDecryptedContent;
    }

    private int countTextWords(String content) {
        return countAlphabetsWords(content, detectAlphabetType(content));
    }

    private List<String> detectAlphabetType(String content) {
        int countEnglishWords = countAlphabetsWords(content, TEXT_WORDS_EN);
        int countUkrainianWords = countAlphabetsWords(content, TEXT_WORDS_UA);
        int countConfigWords = countAlphabetsWords(content, CONFIG_FILES_WORDS);

        if (countConfigWords > countEnglishWords && countConfigWords > countUkrainianWords) {
            return CONFIG_FILES_WORDS;
        } else if (countUkrainianWords > countEnglishWords) {
            return TEXT_WORDS_UA;
        } else {
            return TEXT_WORDS_EN;
        }
    }

    private int countAlphabetsWords(String content, List<String> words) {
        int count = 0;
        for (String word : words) {
            if (content.contains(word)) {
                count++;
            }
        }
        return count;
    }
}
