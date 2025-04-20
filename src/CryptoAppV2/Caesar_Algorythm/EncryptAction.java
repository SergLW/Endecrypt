package CryptoAppV2.Caesar_Algorythm;

public class EncryptAction extends CipherAlgorithm {
    private final int key;

    public EncryptAction(int key) {
        this.key = key;
    }

    @Override
    public String execute(String content) {
        return algorithm(content, key);
    }
}
