package encryption;

import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class EncryptImage {

    public static String stringClient0PublicKey;
    private   String stringClient0PrivateKey;
    private PrivateKey privateKey;

    public Map<String, Object> getRSAKeys() throws Exception {

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

        keyPairGenerator.initialize(512);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        privateKey = keyPair.getPrivate();

        PublicKey publicKey = keyPair.getPublic();

        Base64.Encoder enc = Base64.getEncoder();

        stringClient0PublicKey = enc.encodeToString(publicKey.getEncoded());
        stringClient0PrivateKey = enc.encodeToString(privateKey.getEncoded());
        System.out.println("-----------------------------------");
        System.out.println("Client0 Private Key --> " + stringClient0PrivateKey);
        System.out.println();
        System.out.println("Client0 Public Key --> " + stringClient0PublicKey);
        System.out.println(" ");

        Map<String, Object> Aeskeys = new HashMap<String, Object>();


        Aeskeys.put("private", privateKey);

        Aeskeys.put("public", publicKey);

        return Aeskeys;

    }

    public  byte[] decryptPublicKeys(PublicKey publicKey, byte[] encryptedPublic ) throws Exception {


        Cipher cipher = Cipher.getInstance("RSA");

        cipher.init(Cipher.DECRYPT_MODE,publicKey);


        return cipher.doFinal(encryptedPublic);

    }

    public byte[] decryptAESKey(byte[] encryptedAES) throws Exception {

        Cipher cipher = Cipher.getInstance("RSA");

        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(encryptedAES);

        //return Base64.getEncoder().encodeToString(cipher.doFinal(encryptedAES));

    }

    // Decrypting, encrypted image with AES algorithm in CBC mode
    public  byte[] decryptImage(byte[] encryptedImag , byte[] AESanahtari, IvParameterSpec iviSpec )
            throws InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        SecretKeySpec secretKeySpec3 = new SecretKeySpec(AESanahtari,0,AESanahtari.length,"AES");
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }

        cipher.init(Cipher.DECRYPT_MODE,secretKeySpec3,iviSpec);
        byte[] decriptedImages = cipher.doFinal(encryptedImag);
        return decriptedImages;
    }

    // Save image that decrypted in Client 1 class with AES algorithm .
    public  void saveImage(byte[] bytes) throws IOException {

        FileOutputStream fos = new FileOutputStream("decryptedImage.jpg");
        fos.write(bytes);
        System.out.println("Your image saved securely !!");
        fos.close();

    }

    public  String imageHashIntegrity() throws IOException, NoSuchAlgorithmException {

        File fileName = new File("decryptedImage.jpg");
        byte[] buffer = new byte[8192];
        int count;

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName));
        while ((count = bis.read(buffer)) > 0) {
            digest.update(buffer, 0, count);
        }
        try {
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] hash = digest.digest();

        return new BASE64Encoder().encode(hash);
    }

    public  byte[] decrypteImageWithPublickey(byte[] sifreliHashresim, PublicKey publicKey ) throws InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException {

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE,publicKey);

        return cipher.doFinal(sifreliHashresim);


    }



    // Read image and get byte type
    public byte[] readImagetoByte() {

        File f = new File("src/sample/kurama.jpg");
        InputStream is = null;
        try {
            is = new FileInputStream(f);
        } catch (FileNotFoundException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        byte[] content = null;
        try {
            content = new byte[is.available()];
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            is.read(content);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return content;
    }

    // Encrypted image with AEs CBC mode
    public byte[] encryptImage(byte[] image, byte[] sKeys , IvParameterSpec ivi) {
        try {


            SecretKeySpec secretKeySpec1 = new SecretKeySpec(sKeys,0,sKeys.length,"AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec1, ivi);

            byte[] encrypted = cipher.doFinal(image);
            //   String string = Base64.getEncoder().encodeToString(encrypted);
            return encrypted;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // Take hash of your image
    public String imageHash() throws IOException, NoSuchAlgorithmException {

        File fileName = new File("src/sample/kurama.jpg");
        byte[] buffer = new byte[8192];
        int count;

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName));
        while ((count = bis.read(buffer)) > 0) {
            digest.update(buffer, 0, count);
        }
        try {
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] hash = digest.digest();

        return new BASE64Encoder().encode(hash);
    }


    // Digital signature of our Hash image . ()
    public byte[] encryptImageWithHerPrivateKey(byte[] hashImage) throws Exception {

        Cipher cipher = Cipher.getInstance("RSA");

        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(hashImage);

    }


    //Encrypt AES key with receiver public key by using RSA asymmetric encryption algorithm.
    public  byte[] encryptAESkey(PublicKey publicKey, byte[] sKeys) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidKeyException {


        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedKey = new byte[0];
        try {
            encryptedKey = cipher.doFinal(sKeys);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return encryptedKey;
        //  return Base64.getEncoder().encodeToString(encryptedKey);
    }

}