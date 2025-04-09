import java.util.Scanner;

/**
 * Endecrypt
 */

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String test = scanner.nextLine();

        FileOperation fileOperation = new FileOperation();
        String textFromFile = fileOperation.readFile(test);

        CryptoOperation cryptoOperationFile = new CryptoOperation();

        String encryptedText = cryptoOperationFile.encrypt(textFromFile, 5);
        fileOperation.writeFile(Commande.ENCRYPT, test, encryptedText);

       /* String decryptedText = cryptoOperationFile.decrypt(textFromFile, 5);
       fileOperation.writeFile(Commande.DECRYPT, test, decryptedText); */
    }

}