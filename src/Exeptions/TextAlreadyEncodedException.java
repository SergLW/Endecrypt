package Exeptions;

public class TextAlreadyEncodedException extends RuntimeException {

    public TextAlreadyEncodedException(String message) {
        System.out.println(message);
    }
}

