package EndecryptApp.AlgorithmCes;
/**
 * Класс с алгоритмом encrypt/decrypt
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CryptoAlgorithm {

    /**
     * @param textFromFile - обычный/закодированный текст из файла
     * @param key - ключ шифрования
     * @return - возвращает закодированный/декодированный текст
     */
    public abstract String run(String textFromFile, int key);

    /**
     * @param textFromFile - Извлеченный из файла текст
     * @param key - Ключ смещения ("+" - encrypt, "-" - decrypt)
     * @return -  Возвращает закодированную/декодированную строку в String - реализация для методов encrypt()/decrypt()
     */
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
/*
            if (Alphabet.UPPER_EN.contains(letter)) {
                indexLetter = calculation(Alphabet.UPPER_EN, letter, key); // Расчет результирующего номера буквы с учетом смещения
                if (indexLetter >= 0) {
                    builder.append(Alphabet.UPPER_EN.get(indexLetter));
                } else {
                    builder.append(letter);
                }
            } else if (Alphabet.LOWER_EN.contains(letter)) {
                indexLetter = calculation(Alphabet.LOWER_EN, letter, key); // Расчет результирующего номера буквы с учетом смещения
                if (indexLetter >= 0) {
                    builder.append(Alphabet.LOWER_EN.get(indexLetter));
                } else {
                    builder.append(letter);
                }
            } else if (Alphabet.UPPER_UA.contains(letter)) {
                indexLetter = calculation(Alphabet.UPPER_UA, letter, key); // Расчет результирующего номера буквы с учетом смещения
                if (indexLetter >= 0) {
                    builder.append(Alphabet.UPPER_UA.get(indexLetter));
                } else {
                    builder.append(letter);
                }
            } else if (Alphabet.LOWER_UA.contains(letter)) {
                indexLetter = calculation(Alphabet.LOWER_UA, letter, key); // Расчет результирующего номера буквы с учетом смещения
                if (indexLetter >= 0) {
                    builder.append(Alphabet.LOWER_UA.get(indexLetter));
                } else {
                    builder.append(letter);
                }
            } else if (Alphabet.SYMBOLS.contains(letter)) {
                indexLetter = calculation(Alphabet.SYMBOLS, letter, key); // Расчет результирующего номера буквы с учетом смещения
                if (indexLetter >= 0) {
                    builder.append(Alphabet.SYMBOLS.get(indexLetter));
                } else {
                    builder.append(letter);
                }
            } else {
                builder.append(letter);
            }*/
        }

        return builder.toString();
    }

    /**
     * @param alphabet - Список словаря-константы из класса EndecryptApp.AlgorithmCes.Alphabet
     * @param letter - Один символ из строки полученной из файла
     * @param key - Ключ смещения
     * @return - Результирующий номер буквы с учетом смещения - для метода algorithm()
     */
    private int calculation(List<String> alphabet, String letter, int key) {
        int index = alphabet.indexOf(letter);
        int size = alphabet.size();
        return (index + key + size) % size;
    }


}
