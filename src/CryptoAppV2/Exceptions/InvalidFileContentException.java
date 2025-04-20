package CryptoAppV2.Exceptions;

import static CryptoAppV2.Runner.Constants.EXCEPTION_WRONG_CONTENT;

public class InvalidFileContentException extends RuntimeException{

    public InvalidFileContentException() {
        System.err.println(EXCEPTION_WRONG_CONTENT);
    }

    public InvalidFileContentException(String message) {
        System.err.println(message);
    }
}
