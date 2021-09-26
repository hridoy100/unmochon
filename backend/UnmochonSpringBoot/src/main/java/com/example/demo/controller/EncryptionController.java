package com.example.demo.controller;

import com.example.demo.encryption.CryptoException;
import com.example.demo.encryption.CryptoUtils;
import com.example.demo.encryption.FileEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
public class EncryptionController {

    @RequestMapping(path = "/")
    public String getOut(){
        return "Welcome to unmochon";
    }

    @RequestMapping(path = "/encrypt/{image}")
    public String encryptImage(@PathVariable("image") String image){
        File file = new File("/var/www/html/storage/app/img/"+image);
        String fileName = image.substring(0, image.indexOf("."));

        File encryptedFile = new File("/var/www/html/storage/app/img/"+fileName+".encrypted");
        System.out.println(encryptedFile.getAbsolutePath());
        try {
//            CryptoUtils.encrypt(aeskeyStr, inputFile, encryptedFile);
//            CryptoUtils.decrypt(aeskeyStr, encryptedFile, decryptedFile);
            FileEncryptor.encryptFile("/var/www/html/storage/app/img/"+image, "hridoy");
            System.out.println("Successful");
            return "successful";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return "failed";
    }
    @RequestMapping(path = "/decrypt/{image}")
    public String getImage(@PathVariable("image") String image){
        /*String key = "ryCDVfPcHebM9fWY7sys+Q==";
        String aeskeyStr = Base64.getEncoder().encodeToString(key.getBytes(StandardCharsets.UTF_8));
//        File inputFile = new File("kurama.jpg");
        */
        File encryptedFile = new File("/var/www/html/storage/app/img/"+image);
        String decrypted_name = image.substring(0, image.indexOf(".en"));
        File decryptedFile = new File("/var/www/html/storage/app/img/"+decrypted_name+".jpg");
        System.out.println(encryptedFile.getAbsolutePath());
        System.out.println(decryptedFile.getAbsolutePath());
        try {
//            CryptoUtils.encrypt(aeskeyStr, inputFile, encryptedFile);
//            CryptoUtils.decrypt(aeskeyStr, encryptedFile, decryptedFile);
            FileEncryptor.decryptFile("/var/www/html/storage/app/img/"+decrypted_name, "hridoy");
            return "successful";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return "failed";
    }
}
