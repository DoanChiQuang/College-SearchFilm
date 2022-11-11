/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author Chiquang
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class AlgorithmAESController {
    public String mykey;
    
    public String createKey() throws NoSuchAlgorithmException
    {
        KeyGenerator gen = KeyGenerator.getInstance("AES");
        gen.init(128);
        SecretKey secret = gen.generateKey();        
        byte[] binary = secret.getEncoded();
        String text = String.format("%032X", new BigInteger(+1, binary));
        mykey = text;
        return text;
    }
    
    public String encrypt_String(String strToEncrypt, String myKey) {
      try {
            //MessageDigest sha = MessageDigest.getInstance("SHA-1");
            //byte[] key = myKey.getBytes("UTF-8");
            byte[] key = myKey.getBytes();
            byte[] stringg = strToEncrypt.getBytes();            
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] temp = cipher.doFinal(stringg);
            return Base64.getEncoder().encodeToString(temp);            
      } catch (Exception e) {
            System.out.println(e.toString());
      }
      return null;
    }
    
    public String decrypt_String(String strToDecrypt, String myKey) {
      try {            
            byte[] key = myKey.getBytes();            
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] temp2 = Base64.getDecoder().decode(strToDecrypt.getBytes());
            byte[] temp = cipher.doFinal(temp2);
            String decoded = new String(temp, "UTF-8"); 
            return decoded;            
      } catch (Exception e) {
            System.out.println(e.toString());
      }
      return null;
}
    
//    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, FileNotFoundException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
//    {
//    	AlgorithmAESController a = new AlgorithmAESController();
//    	String key = a.createKey();
//    	System.out.println("keyaes:"+key);
//        String hello = a.encrypt_String("qqqqqqqq", key);
//        System.err.println(hello);
//        System.err.println(a.decrypt_String(hello, key));        
//    }
}
