package Erst_Try_Full.EndecryptApp.AlgorithmCes;

import java.util.List;

public class BruteForce extends CryptoAlgorithm {
    @Override
    public String run(String textFromFile, int key) {
        return algorithm(textFromFile, -key);
    }

    public String decryptWithoutKey(String textFromFile) {
        int maxCount = 0;
        String bestDecryptedText = textFromFile;
        int bestKey = 0;
        int sizeAlphabet = Math.max(Alphabet.UPPER_EN.size(), Alphabet.UPPER_UA.size());
        for (int key = 0; key < sizeAlphabet; key++) {
            String testDecrypted = run(textFromFile, key);
            int count = countTextWords(testDecrypted);
            if (count > maxCount) {
                maxCount = count;
                bestDecryptedText = testDecrypted;
                bestKey = key;
            }
        }
        System.out.printf("Наиболее подходящий ключ: %d\n", bestKey);
        return bestDecryptedText;
    }

    private int countTextWords(String testDecrypted) {
        List<String> alphabetWords = detectAlphabetWords(testDecrypted);
        int count = 0;
        for (String word : alphabetWords) {
            if (testDecrypted.contains(word)) {
                count++;
            }
        }
        return count;
    }

    private List<String> detectAlphabetWords(String testDecrypted) {
        int countEnglish = 0;
        int countUkrainian = 0;
        int countConfig = 0;

        for (String word : Alphabet.TEXT_WORDS_EN) {
            if (testDecrypted.contains(word)) {
                countEnglish++;
            }
        }
        for (String word : Alphabet.TEXT_WORDS_UA) {
            if (testDecrypted.contains(word)) {
                countUkrainian++;
            }
        }
        for (String word : Alphabet.CONFIG_FILES_WORDS) {
            if (testDecrypted.contains(word)) {
                countConfig++;
            }
        }

        if (countConfig > countUkrainian && countConfig > countEnglish) {
            return Alphabet.CONFIG_FILES_WORDS;
        } else if (countUkrainian > countEnglish) {
            return Alphabet.TEXT_WORDS_UA;
        } else {
            return Alphabet.TEXT_WORDS_EN;
        }
    }
}
