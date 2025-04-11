/**
 * Работа с файлами:
 * - чтение файла с преобразованием в строку
 * - запись из строки в файл
 * - переименование файла
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileOperation {

    /**
     * @param pathFile - путь к файлу в String
     * @return - Прочитанный файл преобразован сразу в строку
     */
    public static String readFile(String pathFile) {
        Path path = Path.of(checkPath(pathFile));
        String content = null;
        try {
            content = Files.readString(path);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return content;
    }

    /**
     * @param command - args[0] - тип операции
     * @param pathFile - путь к прочитанному файлу в String
     * @param content - преобразованный текст из прочитанного файла
     */
    public static void writeFile(Commands command, String pathFile, String content) {
        Path path = getPath(command, checkPath(pathFile));
        try {
            Files.writeString(path, content);
            System.out.println("File written to " + path.toString());
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    /**
     * @param command - args[0] - тип операции
     * @param pathFile - путь к прочитанному файлу в String
     * @return - подготавливает путь и переименовывает преобразованный файл, в зависимости от типа операции command
     */
    private static Path getPath(Commands command, String pathFile) {
        Path path = Path.of(checkPath(pathFile));
        String nameFile = path.getFileName().toString();
        int indexDotFile = nameFile.lastIndexOf('.');
        Path dir;
        String renamedFile;

        if (path.getParent() != null) {
            dir = path.getParent();
        } else {
            dir = Path.of(".");
        }

        if (command == Commands.ENCRYPT) {
            if (indexDotFile == -1) {
                renamedFile = nameFile + "_[ENCRYPTED]";
            } else {
                renamedFile = nameFile.substring(0, indexDotFile) + "_[ENCRYPTED]" + nameFile.substring(indexDotFile);
            }
        } else if (command == Commands.DECRYPT || command == Commands.BRUTE_FORCE) {
                renamedFile = nameFile.replace("_[ENCRYPTED]", "_[DECRYPTED]");
        } else {
            renamedFile = nameFile;
        }

        return dir.resolve(renamedFile);
    }

    /**
     * @param pathName путь к файлу
     * @return - возвращает проверенную строку адреса к файлу
     */
    private static String checkPath(String pathName) {
        String checkPath = "";

        if (pathName.startsWith("\"") && pathName.endsWith("\"")) {
            checkPath = pathName.substring(1, pathName.length() - 1);
        } else {
            checkPath = pathName;
        }

        Path path = Path.of(checkPath);
        String fileName = path.getFileName().toString();
        for(char c: Alphabet.INVALID_CHARS) {
            if (fileName.indexOf(c) > 0) {
                throw new IllegalArgumentException("В имени файла есть запрещенные символы!" + c);
            }
        }

        return checkPath;
    }

}
