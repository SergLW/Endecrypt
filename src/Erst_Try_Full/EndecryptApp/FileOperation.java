package Erst_Try_Full.EndecryptApp;

import Erst_Try_Full.EndecryptApp.AlgorithmCes.Alphabet;
import Erst_Try_Full.EndecryptApp.ArgsHandler.Commands;
import Erst_Try_Full.EndecryptApp.Exeptions.InvalidFileExtensionException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileOperation {

    public static String readFile(String pathFile) {
        try {
            Path path = Path.of(checkPath(pathFile));
            if (checkIsTextFile(checkPath(pathFile))) {
                return Files.readString(path);
            } else {
                throw new InvalidFileExtensionException();
            }
        } catch (IOException e) {
                throw new IllegalArgumentException("Ошибка чтения файла");
        }
    }

    public static void writeFile(Commands command, String pathFile, String content) {
        try {
            Path path = getPath(command, checkPath(pathFile));
            Files.writeString(path, content);
            System.out.println("Файл преобразован и сохранен по указанному пути: " + path.toString());
        } catch (IOException e) {
            throw new InvalidFileExtensionException("Не удалось сохранить файл");
        }
    }

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
            if (nameFile.contains("_[ENCRYPTED]")) {
                renamedFile = nameFile.replace("_[ENCRYPTED]", "_[DECRYPTED]");
            } else {
                if (indexDotFile == -1) {
                    renamedFile = nameFile + "_[DECRYPTED]";
                } else {
                    renamedFile = nameFile.substring(0, indexDotFile) + "_[DECRYPTED]" + nameFile.substring(indexDotFile);
                }
            }
        } else {
            renamedFile = nameFile;
        }

        return dir.resolve(renamedFile);
    }

    private static String checkPath(String pathName) {
        String checkedPath = "";

        if (pathName.startsWith("\"") && pathName.endsWith("\"")) {
            checkedPath = pathName.substring(1, pathName.length() - 1);
        } else {
            checkedPath = pathName;
        }

        Path path = Path.of(checkedPath);
        String fileName = path.getFileName().toString();
        for(char c: Alphabet.INVALID_CHARS) {
            if (fileName.indexOf(c) > 0) {
                throw new IllegalArgumentException("В имени файла есть запрещенные символы!" + c);
            }
        }

        return checkedPath;
    }

    private static boolean checkIsTextFile(String pathName) {
        try {
            Path path = Path.of(pathName);
            byte[] bytesCheckedFile = Files.readAllBytes(path);
            int partOfFile = Math.min(bytesCheckedFile.length, 1024);
            for (int i = 0; i < partOfFile; i++) {
                byte b = bytesCheckedFile[i];
                if (b == 0)
                    return false; // есть управляющие символы
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Ошибка чтения файла");
        }
        return true;
    }
}
