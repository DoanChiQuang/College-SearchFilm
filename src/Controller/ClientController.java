/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AlgorithmRSAModel;
import View.ErrorAlert_GUI;
import View.SuccessAlert_GUI;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chiquang
 */
public class ClientController {
    private Socket socket                   = null;
    private BufferedReader in               = null;
    private DataOutputStream out            = null;
    protected String message_from_server    = "";
    protected String message_to_server      = "";
    private String ip                       = "127.0.0.1";
    private int port                        = 5000;
    private AlgorithmAESController aes;
    private String key;  
    public HandleImageController handleImageController = new HandleImageController();
    
    public void connect() {
        try {
            socket = new Socket(ip, port);
            System.out.println("Connected!");                      
            
            in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());                        
            
            handShakeSSL();
        }
        catch(IOException e) {
            System.out.println("Error: " + e);
        } catch (Exception ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void handleImage(String status, String message, String extension) {       
        if(status.equals("Success")) {
            handleImageController.saveFile(message, extension, "image_client");
            new SuccessAlert_GUI().run();
        }
        else if(status.equals("Error")) {                       
            new ErrorAlert_GUI().run();
        }        
    }
    
    public void handShakeSSL() {
        try {
            // Request to handshake ssl
            out.writeBytes("start" + "\n");
            out.flush();

            // Receive and save public key from server
            String publicKey = in.readLine();
            AlgorithmRSAModel rsa = new AlgorithmRSAModel();
            rsa.setPublicKey(publicKey);
            
            // Send secret key to server by encrypt with public key
            aes = new AlgorithmAESController();
            this.key = aes.createKey();            
            String encodeKey = rsa.encrypt(aes.mykey);
            out.writeBytes(encodeKey + "\n");
            out.flush();
            System.out.println("Successfully handshake ssl: " + key);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }        
    }
    
    public void readMessageFromServer () {
        try {
            String message = in.readLine();
            String decodeMessage = aes.decrypt_String(message, key);
            if(!decodeMessage.equals("") && !decodeMessage.equals("null")) {
                String temp[] = decodeMessage.split(";");
                String type = temp[3];
                if(type.equals("image")) {
                    handleImage(temp[0], temp[1], temp[2]);
                }
                this.message_from_server = decodeMessage;
            }
            else {
                this.message_from_server = "null";
            }
        } 
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }    
    
    public void writeMessageToServer(String message) {
        try {                               
            if(!message.equals("") && message!=null) {                
                this.message_to_server = aes.encrypt_String(message, key);
                System.out.println("Client say: " + message_to_server);
                out.writeBytes(message_to_server + "\n");
                out.flush();                
            }
            else {                
                this.message_to_server = aes.encrypt_String("null", key);
                System.out.println("Client say: " + message_to_server);
                out.writeBytes(message_to_server + "\n");
                out.flush();
            }
        } 
        catch (IOException e) {
            System.out.println("Error: " + e);
        } catch (Exception ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeConnect() {        
        try {
            in.close();
            out.close();
            socket.close();
        }
        catch(IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
