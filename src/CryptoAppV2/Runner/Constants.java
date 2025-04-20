package CryptoAppV2.Runner;

import java.util.List;

public class Constants {
    /**
     * Args constants
     */
    public static final List<String> ARG_COMMAND_ALLOWED_TYPES = List.of("ENCRYPT", "DECRYPT", "BRUTE_FORCE");
    public static final int ARG_POSITION_COMMAND_TYPE = 0;
    public static final int ARG_POSITION_FILE_PATH = 1;
    public static final int ARG_POSITION_KEY = 2;
    public static final int ARGS_COUNT_MIN_ALLOWED = 2;
    public static final int ARGS_COUNT_MAX_ALLOWED = 3;
    public static final String ENCRYPTED = "[ENCRYPTED]";
    public static final String DECRYPTED = "[DECRYPTED]";

    /**
     * Exceptions text constants
     */
    public static final String EXCEPTION_UNKNOWN_COMMAND = "Unknown command!";
    public static final String EXCEPTION_INVALID_PATH = "Invalid path!";
    public static final String EXCEPTION_WRONG_CONTENT = "Wrong file content";
    public static final String EXCEPTION_EMPTY_FILE = "File is empty!";
    public static final String EXCEPTION_FAILED_TO_SAVE_FILE = "Failed to save file!";
    public static final String EXCEPTION_INVALID_KEY = "Key is not decimal!";

    /**
     * Text message constants
     */

    public static final String TEXT_FILE_SAVED = "The file has been converted and saved to the path: ";
    public static final String TEXT_WELCOME_CONSOLE = "Welcome to the console mode!";
    public static final String TEXT_CONSOLE_ENTER = "Enter the command or 'exit' to exit:";
    public static final String TEXT_INFO_CONSOLE = """
            Use the following input patterns. Specify the filePath path in quotation marks:
            - 3 parameters:
              ENCRYPT filePath key
              DECRYPT filePath key
            - 2 parameters:
              BRUTE_FORCE filePath""";

}
