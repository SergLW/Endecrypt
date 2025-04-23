package CryptoAppV2.Exceptions;

import static CryptoAppV2.Runner.Constants.EXCEPTION_UNKNOWN_COMMAND;

public class InvalidRunParameterException extends RuntimeException{
    public InvalidRunParameterException() {
        System.err.println(EXCEPTION_UNKNOWN_COMMAND);
    }

    public InvalidRunParameterException(String message) {
        System.err.println(message);
    }
}
