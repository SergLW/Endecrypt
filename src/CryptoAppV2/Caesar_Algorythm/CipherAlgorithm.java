package CryptoAppV2.Caesar_Algorythm;

import CryptoAppV2.Exceptions.InvalidFileContentException;

import static CryptoAppV2.Caesar_Algorythm.Alphabet.*;
import static CryptoAppV2.Runner.Constants.EXCEPTION_EMPTY_FILE;

public abstract class CipherAlgorithm {

    public abstract String execute(String content);

    String algorithm(String content, int key) {

        if (content == null || content.isEmpty()) {
            throw new InvalidFileContentException(EXCEPTION_EMPTY_FILE);
        }

        int maxLength = maxLengthAlphabet(content);
        if (Math.abs(key) > maxLength) {
            key = key % maxLength; // fix key value depending on alphabet length
        }

        StringBuilder builder = new StringBuilder();

        for (char contentLetter : content.toCharArray()) {
            String letter = String.valueOf(contentLetter);
            boolean isFound = false;
            for (String alphabet : ALL_ALPHABETS) {
                if (alphabet.contains(letter)) {
                    int newIndex = calculationNewIndex(alphabet, letter, key);
                    builder.append(alphabet.charAt(newIndex));
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                builder.append(letter);
            }
        }

        return builder.toString();
    }

    /**
     * @param content - From file
     * @return Returns the length of the language used in the text
     */
    int maxLengthAlphabet(String content) {
       int enCount = 0;
       int uaCount = 0;

       for (char c : content.toCharArray()) {
           String letter = String.valueOf(c);
           if (UPPER_EN.contains(letter) || LOWER_EN.contains(letter)) {
               enCount++;
           } else if (UPPER_UA.contains(letter) || LOWER_UA.contains(letter)) {
               uaCount++;
           }
       }
        if (uaCount > enCount) {
            return LOWER_UA.length(); //UA alphabet - size 33
        } else if (enCount > 0) {
            return UPPER_EN.length(); //EU alphabet - size 26
        } else {
            return UPPER_UA.length(); //default UA
        }
    }

    /**
     * @param alphabet - current alphabet
     * @param letter - letter from file content
     * @param key - cipher key
     * @return - new index of letter
     */
    private int calculationNewIndex(String alphabet, String letter, int key) {
        int alphabetIndex = alphabet.indexOf(letter); // index of letter from file content
        int length = alphabet.length();
        if (Math.abs(key) > length) {
            int fixKeyForSmallAlphabet = (key % length + length) % length; // fix key index for smaller alphabets (Symbol, Digits...)
            return (alphabetIndex + fixKeyForSmallAlphabet) % length;
        } else {
            return (alphabetIndex + key + length) % length;
        }
    }
}
