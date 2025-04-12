import AlgorithmCes.*;
import Exeptions.InvalidRunParameterException;
import Exeptions.MissingKeyInTextException;

/**
 * Главный класс приложения
 */
public class CryptoApp {
    private String[] args;
    private static final String addKeyToText = "-k";
    /**
     *
     * @param args - параметры запуска приложения
     */
    public CryptoApp(String[] args) {
        this.args = args;

        argsUse(args[0]);
    }

    /**
     * @param command - принимает значение первого параметра main(String[] args)
     */
    private void argsUse(String command) {

        if (args.length != 0) {
            command = command.toUpperCase();
          switch (command) {
              case "ENCRYPT" -> argsCrypt(Commands.ENCRYPT);
              case "DECRYPT" -> argsCrypt(Commands.DECRYPT);
              case "BRUTE_FORCE" -> argsCrypt(Commands.BRUTE_FORCE);
              case "HELP" -> argsCrypt(Commands.HELP);
              default -> HelpText.defaultStartText();
          }
        }
    }

    /**
     * Логика работы с аргументами параметров main(String[] args)
     * args.length = 4 - вызов методов для ENCRYPT/DECRYPT с параметром -k (с добавлением ключа шифрования в текст)
     * args.length = 3 - вызов методов для ENCRYPT/DECRYPT
     * args.length = 2 - вызов методов для BRUTE_FORCE
     * args.length = 1 - вызов методов HELP
     * @param command - фиксированные значения args[0]
     */
    private void argsCrypt(Commands command) {
        String textFromFile;
        CryptoAlgorithm algorithm;
        KeyMethods keyMethods = new KeyMethods();
        if (args.length == 4) {
            boolean withKey = args[3].equals(addKeyToText);
            textFromFile = FileOperation.readFile(args[1]);
            int key = Integer.parseInt(keyMethods.checkKey(args[2])); // сразу с проверкой Key на число

            if (command == Commands.ENCRYPT && withKey) {
                algorithm = new Encrypt();
                String encryptedText = algorithm.run(textFromFile, key);
                String encryptedTextWithKey = keyMethods.insertKey(encryptedText, key);
                FileOperation.writeFile(Commands.ENCRYPT, args[1], encryptedTextWithKey);
            } else if (command == Commands.DECRYPT && withKey) {
                algorithm = new Decrypt();
                if (keyMethods.haveKey(textFromFile)) {
                    int decryptedKey = keyMethods.decryptKey(textFromFile);
                    if (decryptedKey == key) {
                        String textFromFileWithoutKey = keyMethods.deleteKey(textFromFile);
                        String decryptedText = algorithm.run(textFromFileWithoutKey, key);
                        FileOperation.writeFile(Commands.DECRYPT, args[1], decryptedText);
                    } else {
                        throw new MissingKeyInTextException("Неверный ключ!");
                    }
                }
            } else {
                if (!withKey) {
                    throw new InvalidRunParameterException("Не верный параметр! Для расшифровки текста с ключом в тексте используйте команду 'ENCRYPT path key -k'");
                } else {
                    throw new InvalidRunParameterException("Неизвестная команда: " + command);
                }
            }

        } else if (args.length == 3) {
            textFromFile = FileOperation.readFile(args[1]);
            int key = Integer.parseInt(keyMethods.checkKey(args[2])); // сразу с проверкой Key на число

            if (command == Commands.ENCRYPT) {
                algorithm = new Encrypt();
                String encryptedText = algorithm.run(textFromFile, key);
                FileOperation.writeFile(Commands.ENCRYPT, args[1], encryptedText);
            } else if (command == Commands.DECRYPT) {
                algorithm = new Decrypt();
                String decryptedText = algorithm.run(textFromFile, key);
                FileOperation.writeFile(Commands.DECRYPT, args[1], decryptedText);
            } else {
                throw new InvalidRunParameterException("Неизвестная команда: " + command);
            }

        } else if (args.length == 2) {
            textFromFile = FileOperation.readFile(args[1]);

            if (command == Commands.BRUTE_FORCE) {
                BruteForce bruteForce = new BruteForce();

                if (keyMethods.haveKey(textFromFile)) {
                    int decryptedKey = keyMethods.decryptKey(textFromFile);
                    String textFromFileWithoutKey = keyMethods.deleteKey(textFromFile);
                    String decryptedText = bruteForce.run(textFromFileWithoutKey, decryptedKey);
                    FileOperation.writeFile(Commands.BRUTE_FORCE, args[1], decryptedText);
                    System.out.printf("Файл был зашифрован ключом = %d\n", decryptedKey);
                } else {
                    String decryptedText = bruteForce.runWithoutKey(textFromFile);
                    FileOperation.writeFile(Commands.BRUTE_FORCE, args[1], decryptedText);
                }

            } else {
                throw new InvalidRunParameterException("Неизвестная команда: " + command);
            }

        } else if (args.length == 1) {
            if (command == Commands.HELP) {
                System.out.println("Вызов помощи");
            } else {
                throw new InvalidRunParameterException("Неизвестная команда: " + command);
            }

        } else {
            System.out.println("Usage: encrypt/decrypt path key");
        }
    }
}
