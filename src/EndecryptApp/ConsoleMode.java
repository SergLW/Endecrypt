package EndecryptApp;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleMode {
    public static boolean isConsoleMode;
    ConsoleMode() {
        run();
    }

    private void run() {
        HelpText.defaultStartText();
        Scanner scanner = new Scanner(System.in);
        HelpText.enterText();
        while (true) {
            isConsoleMode = true;
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("exit")) {
                isConsoleMode = false;
                break;
            }

            if (command.isEmpty()) {
                continue;
            }

            String[] args = parseArgs(command);
            //String[] args = command.split(" ");
            new CryptoApp(args);
        }
    }

    private String[] parseArgs(String command) {
        ArrayList<String> args = new ArrayList<>();
        Matcher matcher = Pattern.compile("\"([^\"]*)\"|(\\S+)").matcher(command);
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                args.add(matcher.group(1));
            } else {
                args.add(matcher.group(2));
            }
        }
        return args.toArray(new String[0]);
    }

}
