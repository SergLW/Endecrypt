package Erst_Try_Full.EndecryptApp.AlgorithmCes;
/**
 * encrypt/decrypt algorithm
 */

import java.util.List;

public abstract class CryptoAlgorithm {

    public abstract String run(String textFromFile, int key);

    String algorithm(String textFromFile, int key) {
        StringBuilder builder = new StringBuilder();

        if (textFromFile == null || textFromFile.isEmpty()) {
            throw new IllegalArgumentException("Файл пуст!");
        }

        List<List<String>> alphabets = Alphabet.getAllAlphabets();

        for (char c : textFromFile.toCharArray()) {
            String letter = String.valueOf(c); // Один символ из файла
            boolean isFound = false;

            for (List<String> alphabet : alphabets) {
                if (alphabet.contains(letter)) {
                    int indexLetter = calculation(alphabet,letter,key);
                    if (indexLetter >= 0) {
                        builder.append(alphabet.get(indexLetter));
                    } else {
                        builder.append(letter);
                    }
                    isFound = true;
                    break;
                }
            }
            if (!isFound) builder.append(letter);

        }

        return builder.toString();
    }

    private int calculation(List<String> alphabet, String letter, int key) {
        int index = alphabet.indexOf(letter);
        int size = alphabet.size();
        return (index + key + size) % size;
    }


}
