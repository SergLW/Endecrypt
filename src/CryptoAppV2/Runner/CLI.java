package CryptoAppV2.Runner;

import CryptoAppV2.Exceptions.InvalidRunParameterException;
import CryptoAppV2.Exceptions.WrongFilePathException;

import java.util.ArrayList;
import java.util.Scanner;

import static CryptoAppV2.Runner.Constants.*;

public class CLI {
    public static boolean isConsole;

    public void run() {
        isConsole = true;
        System.out.println(TEXT_WELCOME_CONSOLE);
        System.out.println(TEXT_INFO_CONSOLE);
        Scanner scanner = new Scanner(System.in);
        while (isConsole) {
            System.out.println(TEXT_CONSOLE_ENTER);
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                isConsole = false;
                System.exit(0);
            }

            if (input.isEmpty()) {
                continue;
            }
            try {
                String[] args = inputToArgs(input);
                new ApplicationRunner(args);
            } catch (RuntimeException e) {
            }
        }
    }

    private String[] inputToArgs(String input) {
        ArrayList<String> args = new ArrayList<>();
        int indexFirstSpace = input.indexOf(' ');
        if (indexFirstSpace == -1) {
            throw new InvalidRunParameterException(EXCEPTION_UNKNOWN_COMMAND);
        }
        String command = input.substring(0, indexFirstSpace).toUpperCase();
        args.add(command);
        String restInput = input.substring(indexFirstSpace + 1).trim();

        if (restInput.startsWith("\"")) {
            int indexSecondQuote = restInput.indexOf("\"", 1);
            if (indexSecondQuote == -1) {
                throw new WrongFilePathException(EXCEPTION_INVALID_PATH);
            }
            args.add(restInput.substring(1, indexSecondQuote));
            restInput = restInput.substring(indexSecondQuote + 1).trim();
            if (!restInput.isEmpty()) {
                args.add(restInput);
            }
        } else {
            int indexSecondSpace = restInput.indexOf(' ');
            if (indexSecondSpace == -1) {
                args.add(restInput);
            } else {
                args.add(restInput.substring(0, indexSecondSpace));
                args.add(restInput.substring(indexSecondSpace + 1).trim());
            }
        }

        return args.toArray(new String[0]);
    }
}
