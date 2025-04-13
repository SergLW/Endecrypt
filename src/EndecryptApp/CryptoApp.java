package EndecryptApp;

import EndecryptApp.AlgorithmCes.*;
import EndecryptApp.ArgsHandler.ArgsHandlerMethods;
import EndecryptApp.ArgsHandler.Commands;
import EndecryptApp.Exeptions.InvalidRunParameterException;

/**
 * Главный класс приложения
 */
public class CryptoApp {
    private String[] args;

    /**
     * @param args - параметры запуска приложения
     */
    public CryptoApp(String[] args) {
        this.args = args;
        try {
            argsUse();
        } catch (RuntimeException e) {
            System.out.println("Ошибка: " + e.getMessage());
            if (ConsoleMode.isConsoleMode) {
                HelpText.consoleUseParametersText();
            } else {
                HelpText.useParametersText();
            }
        }
    }

    /**
     *  принимает значение первого параметра main(String[] args)
     */
    private void argsUse() {
        if (args.length != 0) {
            String command = args[0].toUpperCase();
          switch (command) {
              case "ENCRYPT" -> argsCrypt(Commands.ENCRYPT);
              case "DECRYPT" -> argsCrypt(Commands.DECRYPT);
              case "BRUTE_FORCE" -> argsCrypt(Commands.BRUTE_FORCE);
              case "HELP" -> argsCrypt(Commands.HELP);
              default -> throw new InvalidRunParameterException("Неизвестная команда");
          }
        } else {
            new ConsoleMode();
        }
    }

    /**
     * Логика работы с аргументами параметров main(String[] args)
     * args.length = 4 - вызов методов для ENCRYPT/DECRYPT с параметром -k (с добавлением ключа шифрования в текст)
     * args.length = 3 - вызов методов для ENCRYPT/DECRYPT
     * args.length = 2 - вызов методов для BRUTE_FORCE
     * args.length = 1 - вызов метода HELP
     * @param command - фиксированные значения args[0]
     */
    private void argsCrypt(Commands command) {
        KeyMethods keyMethods = new KeyMethods();
        ArgsHandlerMethods argsMethods = new ArgsHandlerMethods();

        switch (args.length) {
            case 4 -> argsMethods.cryptTextWithKey(command, keyMethods, args);
            case 3 -> argsMethods.cryptTextWithoutKey(command, keyMethods, args);
            case 2 -> argsMethods.decryptBruteForce(command, keyMethods, args);
            case 1 -> argsMethods.helpTextCommand(command, args);
            default -> HelpText.useParametersText();
        }
    }
}
