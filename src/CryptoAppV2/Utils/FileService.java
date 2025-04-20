package CryptoAppV2.Utils;

import CryptoAppV2.Caesar_Algorythm.BruteForceAction;
import CryptoAppV2.Exceptions.InvalidFileContentException;
import CryptoAppV2.Runner.CommandType;
import CryptoAppV2.Exceptions.WrongFilePathException;

import static CryptoAppV2.Runner.Constants.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileService {

    public static String readContentFromFile(Path path) {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new InvalidFileContentException(EXCEPTION_WRONG_CONTENT);
        }
    }

    public static void writeContentToFile(Path path, String content) {
        try {
            Files.writeString(path, content);
            System.out.println(TEXT_FILE_SAVED + path);
        } catch (IOException e) {
            System.out.println(EXCEPTION_FAILED_TO_SAVE_FILE);
        }
    }

    public static String checkPathFromArg(String path) {
        if (path.startsWith("\"") && path.endsWith("\"")) {
            return path.substring(1, path.length() - 1);
        } else {
            return path;
        }
    }

    public static void isFileExist(Path path) {
        if (Files.notExists(path)) {
            throw new WrongFilePathException(EXCEPTION_INVALID_PATH);
        }
    }

    public static Path getPathForSave(CommandType command, Path path) {
        String fileName = path.getFileName().toString();
        Path result = path.getParent() != null ? path.getParent() : Path.of(".");
        String newFileName;
        String label;
        int bestKey = BruteForceAction.getLastBestKey();
        String bestKeyForText = "(B Key - " + bestKey + ")";

        if (command == CommandType.ENCRYPT) {
            label = ENCRYPTED;
        } else if (command == CommandType.DECRYPT) {
            label = DECRYPTED;
        } else if (command == CommandType.BRUTE_FORCE && bestKey != -1) {
            label = bestKeyForText;
        } else {
            label = "";
        }

        if (fileName.contains(ENCRYPTED)) {
            newFileName = fileName.replace(ENCRYPTED, label);
        } else {
            newFileName =  insertToFileName(fileName, label);
        }

        return result.resolve(newFileName);
    }

    private static String insertToFileName(String fileName, String label) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex != -1) {
            return fileName.substring(0, dotIndex) + label + fileName.substring(dotIndex);
        } else {
            return fileName + label;
        }
    }
}
