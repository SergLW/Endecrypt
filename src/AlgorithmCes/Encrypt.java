package AlgorithmCes;

import Exeptions.TextAlreadyEncodedException;

public class Encrypt extends CryptoAlgorithm {
    /**
     * @param textFromFile - Извлеченный из файла текст
     * @param key - Ключ смещения
     * @return - Возвращает закодированную строку в String с учетом ключа
     */
    public String run(String textFromFile, int key) {
        return algorithm(textFromFile, key);
    }

}
