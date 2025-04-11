package Exeptions;

public class InvalidFileExtension extends Exception{

    public InvalidFileExtension(String message) {
        System.out.println("Неверное расширение файла: " + message);
    }
}
