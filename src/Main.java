/**
 * Endecrypt
 */

public class Main {

    public static void main(String[] args) {
        String s = "java.lang.System::load has been called by com.intellij.rt.execution.application.AppMainV2 in an unnamed module";
        String s2 = "ofaf$qfsl$Xdxyjr::qtfi mfx gjjs hfqqji gd htr$nsyjqqno$wy$jcjhzynts$fuuqnhfynts$FuuRfnsA2 ns fs zssfrji rtizqj";
        CryptoOperation cryptoOperation = new CryptoOperation(Commande.ENCRYPT, s, "5");
        CryptoOperation cryptoOperation2 = new CryptoOperation(Commande.DECRYPT, s2, "5");
    }

}