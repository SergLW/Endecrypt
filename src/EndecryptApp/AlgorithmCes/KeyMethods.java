package EndecryptApp.AlgorithmCes;

import EndecryptApp.Exeptions.MissingKeyInTextException;
import EndecryptApp.Exeptions.TextAlreadyEncodedException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeyMethods {

    /**
     * @param textFromFile - Полученный String из файла
     * @return - Проверяет содержимое файла на наличие зашифрованного ключа
     */
    public boolean haveKey(String textFromFile) {
        Pattern pattern = Pattern.compile("\\|\\*[0-9A-Fa-f]{1,2}\\*\\|");
        Matcher matcher = pattern.matcher(textFromFile);
        return matcher.find();
    }

    /**
     * @param key - значение ключа args[2]
     * @return - Возвращает исходное значение, если args[2] передано число, а не буква/символ
     */
    public String checkKey(String key) {
        int keyInt;
        try {
            keyInt = Integer.parseInt(key);
            return key;
        } catch (NumberFormatException e) {
            return "Ключ должен быть числом!";
        }
    }

    /**
     * @param encryptedText - Полученный закодированный String
     * @param key - Полученный ключ из параметров args
     * @return - Кодирует ключ в HEX, вставляет его в текст и возвращает закодированный текст вместе с ключем
     */
    public String insertKey(String encryptedText, int key) {
        String hexKey = Integer.toHexString(key*9).toUpperCase();
        String keyMarker = "|*" + hexKey + "*|";
        int positionMarker = Math.max(1, encryptedText.length() /4);
        String finishText = encryptedText;

        if (haveKey(encryptedText)) {
            throw new TextAlreadyEncodedException("Текст уже закодирован!");
        } else {
            finishText = encryptedText.substring(0, positionMarker) + keyMarker + encryptedText.substring(positionMarker);
        }
        return finishText;
    }

    /**
     * @param encryptedText - Полученный закодированный String
     * @return - Находит, декодирует и возвращает значение ключа.
     */
    public int decryptKey(String encryptedText) {
        if (haveKey(encryptedText)) {
            int startKey = encryptedText.indexOf("|*");
            int endKey = encryptedText.indexOf("*|", startKey + 2);
            int decryptedKey = 0;
            if (startKey > 0 && endKey > startKey) {
                String hexKey = encryptedText.substring(startKey + 2, endKey);
                decryptedKey = (Integer.parseInt(hexKey, 16)) / 9;
            }
            return decryptedKey;
        } else {
            throw new MissingKeyInTextException("Текст не закодирован или отсутствует ключ!");
        }
    }

    /**
     * @param encryptedText - Полученный закодированный String
     * @return - Удаляет из закодированного текста ключ и возвращает строку
     */
    public String deleteKey(String encryptedText) {
        if (haveKey(encryptedText)) {
            int startKey = encryptedText.indexOf("|*");
            int endKey = encryptedText.indexOf("*|", startKey + 2);
            String textFromFileWithoutKey = encryptedText;

            if (startKey > 0 && endKey > startKey) {
                textFromFileWithoutKey = encryptedText.substring(0, startKey) + encryptedText.substring(endKey);
            }
            return textFromFileWithoutKey;
        } else {
            throw new MissingKeyInTextException("Текст не закодирован или отсутствует ключ!");
        }
    }

}
