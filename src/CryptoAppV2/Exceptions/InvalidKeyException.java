package CryptoAppV2.Exceptions;

import static CryptoAppV2.Runner.Constants.EXCEPTION_INVALID_KEY;

public class InvalidKeyException extends RuntimeException{

    public InvalidKeyException() {
        System.err.println(EXCEPTION_INVALID_KEY);
    }

    public InvalidKeyException(String message) {
        System.err.println(message);
    }
}
