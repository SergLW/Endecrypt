package CryptoAppV2.Runner;

import CryptoAppV2.Caesar_Algorythm.BruteForceAction;
import CryptoAppV2.Caesar_Algorythm.CipherAlgorithm;
import CryptoAppV2.Caesar_Algorythm.DecryptAction;
import CryptoAppV2.Caesar_Algorythm.EncryptAction;

import java.nio.file.Path;
import java.util.Map;

import static CryptoAppV2.Runner.CLI.isConsole;
import static CryptoAppV2.Runner.CommandType.*;
import static CryptoAppV2.Runner.Constants.*;
import static CryptoAppV2.Utils.CipherKeyValidator.*;
import static CryptoAppV2.Utils.FileService.*;

public class ApplicationRunner {
    private String[] args;

    public ApplicationRunner(String[] args) {
        this.args = args;
        try {
            run();
        } catch (RuntimeException e) {
            if (!isConsole) {
                //e.printStackTrace();
                //System.err.println(e.getClass().getSimpleName());
                System.exit(1);
            }
        }
    }

    public void run() {
        if (args.length == 0) {
            if (!isConsole) {
                new CLI().run();
            }
        }
        checkArgsCount(args);

        CommandType commandType = isArgumentValid(args[ARG_POSITION_COMMAND_TYPE]);
        String checkedPath = checkPathFromArg(args[ARG_POSITION_FILE_PATH]);
        Path path = Path.of(checkedPath);
        isFileExist(path);

        String content = readContentFromFile(path);

        int key = getKeyFromArgs(commandType, args);

        Map<CommandType, CipherAlgorithm> operation = Map.of(
                ENCRYPT, new EncryptAction(key),
                DECRYPT, new DecryptAction(key),
                BRUTE_FORCE, new BruteForceAction()
        );

        String resultContent = operation.get(commandType).execute(content);
        Path pathForSave = getPathForSave(commandType, path);
        writeContentToFile(pathForSave, resultContent);
    }

}
