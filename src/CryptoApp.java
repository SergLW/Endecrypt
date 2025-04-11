/**
 * Главный класс приложения
 */
public class CryptoApp {
    private String[] args;

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
     * args.length = 3 - вызов методов для ENCRYPT/DECRYPT
     * args.length = 2 - вызов методов для BRUTE_FORCE
     * args.length = 1 - вызов методов HELP
     * @param command - фиксированные значения args[0]
     */
    private void argsCrypt(Commands command) {
        String textFromFile;
        if (args.length == 3) {
            textFromFile = FileOperation.readFile(args[1]);
            int key = Integer.parseInt(checkKey(args[2])); // сразу с проверкой Key на число
            if (command == Commands.ENCRYPT) {
                String encryptedText = CryptoOperation.encrypt(textFromFile, key);
                FileOperation.writeFile(Commands.ENCRYPT, args[1], encryptedText);
            } else if (command == Commands.DECRYPT) {
                String decryptedText = CryptoOperation.decrypt(textFromFile, key);
                FileOperation.writeFile(Commands.DECRYPT, args[1], decryptedText);
            } else {
                System.out.println("Unknown command: " + command);
            }
        } else if (args.length == 2) {
            textFromFile = FileOperation.readFile(args[1]);
            if (command == Commands.BRUTE_FORCE) {
                System.out.println("Nicht fertig");
            } else {
                System.out.println("Unknown command: " + command);
            }
        } else if (args.length == 1) {
            if (command == Commands.HELP) {
                System.out.println("Вызов помощи");
            } else {
                System.out.println("Unknown command: " + command);
            }
        } else {
            System.out.println("Usage: encrypt/decrypt path key");
        }
    }

    /**
     * @param key - значение ключа args[2]
     * @return - Возвращает исходное значение, если args[2] передано число, а не буква/символ
     */
    private String checkKey(String key) {
        int keyInt;
        try {
            keyInt = Integer.parseInt(key);
            return key;
        } catch (NumberFormatException e) {
            return "Ключ должен быть числом!";
        }
    }


}
