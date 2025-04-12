package Exeptions;

public class InvalidFileExtensionException extends RuntimeException {

    public InvalidFileExtensionException() {
        System.out.println("Это бинарный файл!");
    }
}
