package encryption;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * A tester for the CryptoUtils class.
 * @author www.codejava.net
 *
 */
public class CryptoUtilsTest {
    public static void main(String[] args) {
        /*Using predefined key*/
//        String key = "6874lCJJvHm1C8fiDM";
        String key = "ryCDVfPcHebM9fWY7sys+Q==";

        /*Comment out for using predefined key*/
//        GenerateKey key = new GenerateKey();
//        byte[] AesKey = key.generateAes128Key();
//        String aeskeyStr = Base64.getEncoder().encodeToString(AesKey);
        /*Comment out for using predefined key*/

        /*Using predefined key*/
        String aeskeyStr = Base64.getEncoder().encodeToString(key.getBytes(StandardCharsets.UTF_8));
        File inputFile = new File("2021-09-23 02 27 12.jpg");
        File encryptedFile = new File("image_1632342445961.encrypted");
        File decryptedFile = new File("image_1632342445961.jpg");

        try {
            CryptoUtils.encrypt(aeskeyStr, inputFile, encryptedFile);
            CryptoUtils.decrypt(aeskeyStr, encryptedFile, decryptedFile);
        } catch (CryptoException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        /*GenerateKey key = new GenerateKey();
        byte[] AesKey = key.generateAes128Key();
        String aeskeyStr = Base64.getEncoder().encodeToString(AesKey);
        System.out.println("-----------------------------------");
        System.out.println("AES key in Client 0 --> " + aeskeyStr);*/
//        byte[] imageByte = client_0.readImagetoByte();
//        String imageStr = Base64.getEncoder().encodeToString(imageByte);
//        System.out.println("Original image --> " + imageStr);
    }

    public File encryptFile(File inputFile){

        /*String key = "6874lCJJvHm1C8fiDM";
        /*String key = "ryCDVfPcHebM9fWY7sys+Q==";

        String aeskeyStr = Base64.getEncoder().encodeToString(key.getBytes(StandardCharsets.UTF_8));
        */
        GenerateKey key = new GenerateKey();
        byte[] AesKey = key.generateAes128Key();
        String aeskeyStr = Base64.getEncoder().encodeToString(AesKey);
        System.out.println("key: " + aeskeyStr);
        File encryptedFile = new File("image_"+System.currentTimeMillis()+".encrypted");
//        File decryptedFile = new File("decrypted2.jpg");

        try {
            CryptoUtils.encrypt(aeskeyStr, inputFile, encryptedFile);
            return encryptedFile;
//            CryptoUtils.decrypt(aeskeyStr, encryptedFile, decryptedFile);
        } catch (CryptoException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

}