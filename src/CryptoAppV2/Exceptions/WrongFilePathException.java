package CryptoAppV2.Exceptions;

import static CryptoAppV2.Runner.Constants.EXCEPTION_INVALID_PATH;

public class WrongFilePathException extends RuntimeException{
    public WrongFilePathException() {
        System.err.println(EXCEPTION_INVALID_PATH);
    }

    public WrongFilePathException(String message) {
        System.err.println(message);
    }
}
