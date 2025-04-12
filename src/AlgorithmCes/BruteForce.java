package AlgorithmCes;

import java.util.List;

public class BruteForce extends CryptoAlgorithm {
    @Override
    public String run(String textFromFile, int key) {
        return algorithm(textFromFile, -key);
    }

    /**
     * @param textFromFile - Закодированный текси без встроенного ключа
     * @return - возвращает лучший результат псоле перебора возможных ключей
     */
    public String runWithoutKey(String textFromFile) {
        int maxCount = 0;
        String bestDecryptedText = textFromFile;
        int bestKey = 0;
        int sizeAlphabet = Math.max(Alphabet.UPPER.size(), Alphabet.SYMBOLS.size());
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

    /**
     * @param testDecrypted - Пробный декодированный текст
     * @return - считает количество совпадений со словарем часто встречающихся слов
     */
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

    /**
     * @param testDecrypted - Пробный декодированный текст
     * @return - В зависимости от типа файла - простой текст или конфигурационный файл - возвращает
     * предпочтительный список слов.
     */
    private List<String> detectAlphabetWords(String testDecrypted) {
        int countEnglish = 0;
        int countConfig = 0;
        for (String word : Alphabet.ENGLISH_TEXT_WORDS) {
            if (testDecrypted.contains(word)) {
                countEnglish++;
            }
        }
        for (String word : Alphabet.CONFIG_FILES_WORDS) {
            if (testDecrypted.contains(word)) {
                countConfig++;
            }
        }
        if (countEnglish > countConfig) {
            return Alphabet.ENGLISH_TEXT_WORDS;
        } else {
            return Alphabet.CONFIG_FILES_WORDS;
        }
    }
}
