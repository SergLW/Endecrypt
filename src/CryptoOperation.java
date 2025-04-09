/**
 * Класс с алгоритмом encrypt/decrypt
 */

import java.util.List;

public class CryptoOperation {
    private String textFromFile;
    private String keyFromArgs;
    private Commande command;
    /**
     * @param command - args[0] - первый параметр запуска файла - тип операции
     * @param textFromFile - Извлеченный из файла текст
     * @param keyFromArgs - args[2] - третий параметр запуска файла - ключ смещения
     */
    public CryptoOperation(Commande command, String textFromFile, String keyFromArgs) {
        this.command = command;
        this.textFromFile = textFromFile;
        this.keyFromArgs = keyFromArgs;

        int key = Integer.parseInt(keyFromArgs);
        if (command == Commande.ENCRYPT) {
            System.out.println(encrypt(textFromFile, key));
        } else if (command == Commande.DECRYPT) {
            System.out.println(decrypt(textFromFile, key));
        } else if (command == Commande.BRUTE_FORCE) {
            System.out.println("Не реализовано");
        } else {
            System.out.printf("Unknown command %s\n", command);
        }
    }
    /**
     * @param textFromFile - Извлеченный из файла текст
     * @param key - Ключ смещения
     * @return - Возвращает закодированную строку в String с учетом ключа
     */
    public String encrypt(String textFromFile, int key) {
        return algorithm(textFromFile, key);
    }
    /**
     * @param textFromFile - Извлеченный из файла текст
     * @param key - Ключ смещения
     * @return - Возвращает декодированную строку в String с учетом ключа
     */
    public String decrypt(String textFromFile,  int key) {
        return algorithm(textFromFile, -key);
    }

    /**
     * @param textFromFile - Извлеченный из файла текст
     * @param key - Ключ смещения ("+" - encrypt, "-" - decrypt)
     * @return -  Возвращает закодированную/декодированную строку в String - реализация для методов encrypt()/decrypt()
     */
    private String algorithm(String textFromFile, int key) {
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
    private int calculation(List<String> alphabet, String letter, int key) {
        int index = alphabet.indexOf(letter);
        int size = alphabet.size();
        return (index + key + size) % size;
    }

}
