package CryptoAppV2.Utils;

import CryptoAppV2.Exceptions.InvalidKeyException;
import CryptoAppV2.Runner.CommandType;
import CryptoAppV2.Exceptions.InvalidRunParameterException;

import static CryptoAppV2.Runner.CommandType.BRUTE_FORCE;
import static CryptoAppV2.Runner.Constants.*;

public class CipherKeyValidator {

    public static void checkArgsCount(String[] args) {
        if (args.length < ARGS_COUNT_MIN_ALLOWED || args.length > ARGS_COUNT_MAX_ALLOWED) {
            throw new InvalidRunParameterException(EXCEPTION_UNKNOWN_COMMAND);
        }
    }

    public static CommandType isArgumentValid(String arg) {
        if (!ARG_COMMAND_ALLOWED_TYPES.contains(arg.toUpperCase())) {
            throw new InvalidRunParameterException(EXCEPTION_UNKNOWN_COMMAND);
        } else {
            return CommandType.fromString(arg);
        }
    }

    public static String isCaesarKeyNumber(String key) {
        try {
            if (key.isEmpty()) {
                throw new InvalidRunParameterException(EXCEPTION_UNKNOWN_COMMAND);
            }
            int keyInt = Integer.parseInt(key);
            return key;
        } catch (NumberFormatException e) {
            throw new InvalidKeyException(EXCEPTION_INVALID_KEY);
        }
    }

    public static int getKeyFromArgs(CommandType commandType, String[] args) {
        if (commandType != BRUTE_FORCE && args.length > ARGS_COUNT_MIN_ALLOWED) {
            return Integer.parseInt(isCaesarKeyNumber(args[ARG_POSITION_KEY]));
        } else if (commandType != BRUTE_FORCE) {
            throw new InvalidRunParameterException(EXCEPTION_UNKNOWN_COMMAND);
        } else return 0;
    }

}
