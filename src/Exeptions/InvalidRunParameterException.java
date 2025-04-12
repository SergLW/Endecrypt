package Exeptions;

public class InvalidRunParameterException extends RuntimeException {
    public InvalidRunParameterException(String message) {
        System.out.println(message);
    }
}
