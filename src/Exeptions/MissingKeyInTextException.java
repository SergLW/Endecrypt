package Exeptions;

public class MissingKeyInTextException extends RuntimeException {
    public MissingKeyInTextException(String message) {
        System.out.println(message);
    }
}
