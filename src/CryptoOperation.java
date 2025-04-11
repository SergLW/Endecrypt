/**
 * Класс с алгоритмом encrypt/decrypt
 */

import java.util.List;

public class CryptoOperation {

    public CryptoOperation() {

    }

    /**
     * @param textFromFile - Извлеченный из файла текст
     * @param key - Ключ смещения
     * @return - Возвращает закодированную строку в String с учетом ключа
     */
    public static String encrypt(String textFromFile, int key) {
        return algorithm(textFromFile, key);
    }

    /**
     * @param textFromFile - Извлеченный из файла текст
     * @param key - Ключ смещения
     * @return - Возвращает декодированную строку в String с учетом ключа
     */
    public static String decrypt(String textFromFile,  int key) {
        return algorithm(textFromFile, -key);
    }

    /**
     * @param textFromFile - Извлеченный из файла текст
     * @param key - Ключ смещения ("+" - encrypt, "-" - decrypt)
     * @return -  Возвращает закодированную/декодированную строку в String - реализация для методов encrypt()/decrypt()
     */
    private static String algorithm(String textFromFile, int key) {
        StringBuilder builder = new StringBuilder();

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
                builder.append(Alphabet.SYMBOLS.get(indexLetter));
            } else {
                builder.append(letter);
            }
        }
        return builder.toString();
    }

    /**
     * @param alphabet - Список словаря-константы из класса Alphabet
     * @param letter - Один символ из строки полученной из файла
     * @param key - Ключ смещения
     * @return - Результирующий номер буквы с учетом смещения - для метода algorithm()
     */
    private static int calculation(List<String> alphabet, String letter, int key) {
        int index = alphabet.indexOf(letter);
        int size = alphabet.size();
        return (index + key + size) % size;
    }
}
