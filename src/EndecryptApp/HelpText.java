package EndecryptApp;

/**
 * Файл с описанием помощи и подсказками
 * */
public class HelpText {
    public static final String WELCOME = "Добро пожаловать в консольный режим приложения!";
    public static final String ENDECRYPT = """
                *****************************
                *------- Endecrypt ---------*
                * Encrypt/Decrypt textfiles *
                *****************************""";
    public static final String COMMANDDESCRIPTION = """
            Приложение для кодирования и декодирования текстовых форматов файлов,
            используя шифр Цезаря.
            Предусмотрены следующие параметры:
                - command:
                  - ENCRYPT - кодирует файл с указанным ключом
                  - DECRYPT - декодирует файл с указанным ключом
                  - BRUTE_FORCE - программа сама вычисляет ключ и декодирует файл
                  - HELP - вызов помощи с описанием комад
                - filePath: путь к файлу, который требуется закодировать/декодировать.
                     При использовании пути с недопустимыми символами или пробелами, путь
                     следует указазывать в кавычках "". Следует учитывать, что Windows не
                     допускает использование опредленных символов в названиях файлов.
                - key: ключ смещения для кодирования текста
                - k: boolean-параметр, использовать, есть нужно добавить зашифрованный ключ в
                     в текст из файла после кодирования. При попытке декодировать текст с встроенным ключом,
                     он будет проверен и, в случае неверного значения, файл не будет декодирован.
                     При кодировании/декодировании без данного параметра не проверяется наличие встроенного ключа.
            """;
    public static final String USEDESCRIPTION = """
            Чтобы использовать приложение, используйте следующие шаблоны ввода:
                - 4 параметра:
                  - java -jar myApp.jar ENCRYPT filePath key -k
                  - java -jar myApp.jar DECRYPT filePath key -k
                - 3 параметра:
                  - java -jar myApp.jar ENCRYPT filePath key
                  - java -jar myApp.jar DECRYPT filePath key
                - 2 параметра:
                  - java -jar myApp.jar BRUTE_FORCE filePath
                - 1 параметр:
                  - java -jar myApp.jar HELP""";
    public static final String USEDESCRIPTIONCONSOLEMODE = """
            Чтобы использовать приложение, используйте следующие шаблоны ввода.
            Путь filePath указывайте в кавычках:
                - 4 параметра:
                  ENCRYPT filePath key -k
                  DECRYPT filePath key -k
                - 3 параметра:
                  ENCRYPT filePath key
                  DECRYPT filePath key
                - 2 параметра:
                  BRUTE_FORCE filePath
                - 1 параметр:
                  HELP""";

    public static final String ENTERCOMMAND = "Введите команду или 'exit' для выхода:";

    public static void defaultStartText() {
        System.out.printf("%s\n%s\n", WELCOME, ENDECRYPT);
        System.out.printf("%s\n%s\n", COMMANDDESCRIPTION, USEDESCRIPTIONCONSOLEMODE);
    }

    public static void commandUseDescriptionText() {
        System.out.printf("%s\n%s\n", COMMANDDESCRIPTION, USEDESCRIPTION);
    }

    public static void useParametersText() {
        System.out.printf("%s\n", USEDESCRIPTION);
    }

    public static void consoleUseParametersText() {
        System.out.printf("%s\n", USEDESCRIPTIONCONSOLEMODE);
    }

    public static void enterText() {
        System.out.printf("%s\n", ENTERCOMMAND);
    }
}
