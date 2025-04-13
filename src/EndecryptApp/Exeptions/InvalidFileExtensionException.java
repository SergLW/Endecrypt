package EndecryptApp.Exeptions;

public class InvalidFileExtensionException extends RuntimeException {

    public InvalidFileExtensionException() {
        System.out.println("Это бинарный файл!");
    }

    public InvalidFileExtensionException(String message) {
        System.out.println(message);
    }
}
