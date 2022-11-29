package Model;

import java.io.File;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import java.security.interfaces.RSAPrivateKey;
import Controller.AlgorithmRSAController;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
// test

public class AlgorithmRSAModel {

    public PublicKey publickey;
    public PrivateKey privatekey;

    public AlgorithmRSAModel() throws Exception {
        this.privatekey = this.getPrivateKey();
        this.publickey  = this.getPublicKey();
    }
        
    public PrivateKey getPrivateKey() throws Exception {
        byte[] keyBytes = Files.readAllBytes(new File(AlgorithmRSAController.PRIVATE_KEY_FILE).toPath());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        privatekey = kf.generatePrivate(spec);
        return kf.generatePrivate(spec);
    }
    
    public PublicKey getPublicKey() throws Exception {
        byte[] keyBytes = Files.readAllBytes(new File(AlgorithmRSAController.PUBLIC_KEY_FILE).toPath());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        publickey = kf.generatePublic(spec);
        return kf.generatePublic(spec);
    }
    
    public String getPrivateKey_ToString() {
        PrivateKey privatekey = this.privatekey;
        RSAPrivateKey rsaPri = (RSAPrivateKey)(privatekey);
        return String.valueOf(rsaPri.getPrivateExponent());
    }
        
    public String getPublicKey_ToString() {
    	PublicKey publickey2 = this.publickey;
    	byte[] byte_key = publickey2.getEncoded();
    	String str_key  = Base64.getEncoder().encodeToString(byte_key);
    	return str_key;
    }        
    
    public String encrypt(String input) throws InvalidKeyException, Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publickey);
        byte encrypt[] = cipher.doFinal(input.getBytes());        
        String encrypted = Base64.getEncoder().encodeToString(encrypt);
        return encrypted;
    }    
        
    public String decrypt(String input) throws InvalidKeyException, Exception {    	
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privatekey);
        byte[] decode = Base64.getDecoder().decode(input);
        byte[] byteDecrypted = cipher.doFinal(decode);
        String decrypted = new String(byteDecrypted);
        return decrypted;
    }
    
    public void setPublicKey(String input){    	
        try {
            byte[] byte_key = Base64.getDecoder().decode(input);
            KeyFactory factory;
            factory = KeyFactory.getInstance("RSA");
            PublicKey public_key;
            public_key = (PublicKey) factory.generatePublic(new X509EncodedKeySpec(byte_key));
            this.publickey = public_key;
        } catch (InvalidKeySpecException e) {                
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {            
            e.printStackTrace();
        }    	
    }
    
    
//    public static void main(String[] args) throws Exception {
//        AlgorithmRSAModel a = new AlgorithmRSAModel();
//        a.getPublicKey();
//        a.getPrivateKey();
//        System.out.println("Private key: " + a.getPrivateKey_ToString());
//        System.out.println("Public key: " + a.getPublicKey_ToString());
//        System.out.println("Plain: hello");
//        String test = a.encrypt("hello");
//        System.out.println("Cipher: " + test);
//        System.out.println("Decrypt:" + a.decrypt(test));
//    }
}
