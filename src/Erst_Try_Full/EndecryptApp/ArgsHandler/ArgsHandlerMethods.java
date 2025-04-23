package Erst_Try_Full.EndecryptApp.ArgsHandler;

import Erst_Try_Full.EndecryptApp.AlgorithmCes.*;
import Erst_Try_Full.EndecryptApp.ConsoleMode;
import Erst_Try_Full.EndecryptApp.HelpText;
import Erst_Try_Full.EndecryptApp.Exeptions.InvalidRunParameterException;
import Erst_Try_Full.EndecryptApp.Exeptions.MissingKeyInTextException;
import Erst_Try_Full.EndecryptApp.FileOperation;

public class ArgsHandlerMethods {
    private static final String addKeyToText = "-k";

    public void cryptTextWithKey(Commands command, KeyMethods keyMethods, String[] args) {
        if (args.length != 4) {
            throw new InvalidRunParameterException("Неизвестная команда");
        }
        boolean withKey = args[3].equals(addKeyToText);
        if (!withKey) {
            throw new InvalidRunParameterException("Для расшифровки текста с ключом в тексте используйте параметр -k");
        }

        String textFromFile = FileOperation.readFile(args[1]);
        int key = Integer.parseInt(keyMethods.checkKey(args[2])); // сразу с проверкой Key на число

        CryptoAlgorithm algorithm = switch (command) {
            case ENCRYPT -> new Encrypt();
            case DECRYPT -> new Decrypt();
            default -> throw new InvalidRunParameterException("Неизвестная команда: " + command);
        };

        if (command == Commands.ENCRYPT) {
            String encryptedText = algorithm.run(textFromFile, key);
            String encryptedTextWithKey = keyMethods.insertKey(encryptedText, key);
            FileOperation.writeFile(command, args[1], encryptedTextWithKey);

        } else if (command == Commands.DECRYPT) {
            if (!keyMethods.haveKey(textFromFile)) {
                throw new MissingKeyInTextException("Ключ неверен или отсутствует!");
            }

            int decryptedKey = keyMethods.decryptKey(textFromFile);
            if (decryptedKey != key) {
                throw new MissingKeyInTextException("Ключ неверен!");
            }

            String textFromFileWithoutKey = keyMethods.deleteKey(textFromFile);
            String decryptedText = algorithm.run(textFromFileWithoutKey, key);
            FileOperation.writeFile(command, args[1], decryptedText);
        }
    }

    public void cryptTextWithoutKey(Commands command, KeyMethods keyMethods, String[] args) {
        if (args.length != 3) {
            throw new InvalidRunParameterException("Неизвестная команда");
        }
        String textFromFile = FileOperation.readFile(args[1]);
        int key = Integer.parseInt(keyMethods.checkKey(args[2])); // сразу с проверкой Key на число

        CryptoAlgorithm algorithm = switch (command) {
            case ENCRYPT -> new Encrypt();
            case DECRYPT -> new Decrypt();
            default -> throw new InvalidRunParameterException("Неизвестная команда");
        };

        String encryptedText = algorithm.run(textFromFile, key);
        if (command == Commands.DECRYPT && ConsoleMode.isConsoleMode) {
            System.out.println("Файл декодирован без проверки ключа!");
        }
        FileOperation.writeFile(command, args[1], encryptedText);
    }

    public void decryptBruteForce(Commands command, KeyMethods keyMethods, String[] args) {
        if (args.length != 2) {
            throw new InvalidRunParameterException("Неизвестная команда");
        }
        String textFromFile = FileOperation.readFile(args[1]);

        if (command != Commands.BRUTE_FORCE) {
            throw new InvalidRunParameterException("Неизвестная команда: " + command);
        }
        BruteForce bruteForce = new BruteForce();
        String decryptedText;
        if (keyMethods.haveKey(textFromFile)) {
            int decryptedKey = keyMethods.decryptKey(textFromFile);
            String textFromFileWithoutKey = keyMethods.deleteKey(textFromFile);
            decryptedText = bruteForce.run(textFromFileWithoutKey, decryptedKey);
            System.out.printf("Файл был зашифрован ключом = %d\n", decryptedKey);
        } else {
            decryptedText = bruteForce.decryptWithoutKey(textFromFile);
        }
        FileOperation.writeFile(Commands.BRUTE_FORCE, args[1], decryptedText);
    }

    public void helpTextCommand(Commands command, String[] args) {
        if (args.length != 1) {
            throw new InvalidRunParameterException("Неизвестная команда");
        }
        if (command != Commands.HELP) {
            throw new InvalidRunParameterException("Неизвестная команда: " + command);
        }
        if (ConsoleMode.isConsoleMode) {
            HelpText.consoleUseParametersText();
        } else {
            HelpText.commandUseDescriptionText();
        }
    }
}
