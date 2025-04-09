public class CryptoApp {
    private String command;
    private String pathFile;
    private String key;

    public CryptoApp(String command, String pathFile, String key) {
        this.command = command;
        this.pathFile = pathFile;
        this.key = key;
    }

    public CryptoApp(String command, String pathFile) {
        this.command = command;
        this.pathFile = pathFile;
    }

    public void argsUse() {

    }


}
