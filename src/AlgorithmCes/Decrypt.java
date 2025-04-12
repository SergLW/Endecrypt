package AlgorithmCes;

import Exeptions.MissingKeyInTextException;

public class Decrypt extends CryptoAlgorithm {
    /**
     * @param textFromFile - Извлеченный из файла текст
     * @param key - Ключ смещения
     * @return - Возвращает декодированную строку в String с учетом ключа
     */
    public String run(String textFromFile,  int key) {
        return algorithm(textFromFile, -key);
    }

}
