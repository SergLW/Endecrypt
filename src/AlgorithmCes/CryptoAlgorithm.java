package AlgorithmCes;
/**
 * Класс с алгоритмом encrypt/decrypt
 */

import Exeptions.MissingKeyInTextException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if (textFromFile != null) {
            for (char c : textFromFile.toCharArray()) {
                String letter = String.valueOf(c); // Один символ из файла
                int indexLetter;
                if (Alphabet.UPPER.contains(letter)) {
                    indexLetter = calculation(Alphabet.UPPER, letter, key); // Расчет результирующего номера буквы с учетом смещения
                    builder.append(Alphabet.UPPER.get(indexLetter));
                } else if (Alphabet.LOWER.contains(letter)) {
                    indexLetter = calculation(Alphabet.LOWER, letter, key); // Расчет результирующего номера буквы с учетом смещения
                    builder.append(Alphabet.LOWER.get(indexLetter));
                } else if (Alphabet.SYMBOLS.contains(letter)) {
                    indexLetter = calculation(Alphabet.SYMBOLS, letter, key); // Расчет результирующего номера буквы с учетом смещения
                    if (indexLetter >= 0) {
                        builder.append(Alphabet.SYMBOLS.get(indexLetter));
                    } else {
                        builder.append(letter);
                    }
                } else {
                    builder.append(letter);
                }
            }
        } else {
            throw new IllegalArgumentException("Файл пуст!");
        }

        return builder.toString();
    }

    /**
     * @param alphabet - Список словаря-константы из класса AlgorithmCes.Alphabet
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
