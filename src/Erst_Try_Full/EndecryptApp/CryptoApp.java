package Erst_Try_Full.EndecryptApp;

import Erst_Try_Full.EndecryptApp.AlgorithmCes.*;
import Erst_Try_Full.EndecryptApp.ArgsHandler.ArgsHandlerMethods;
import Erst_Try_Full.EndecryptApp.ArgsHandler.Commands;
import Erst_Try_Full.EndecryptApp.Exeptions.InvalidRunParameterException;

/**
 * First_Try_Full.Main class
 */
public class CryptoApp {
    private String[] args;

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
