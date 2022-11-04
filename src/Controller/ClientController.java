/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AlgorithmRSAModel;
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
    public static String PUBLIC_KEY_FILE    = "./KEYRSA/publicKeyClient.txt";
    public static String PRIVATE_KEY_FILE   = "./KEYRSA/privateKeyClient.txt";     
    public AlgorithmRSAModel algorithmRSAModel              = null;
    public AlgorithmRSAController algorithmRSAController    = null;
    public void connect() {
        try {
            socket = new Socket(ip, port);
            System.out.println("Connected!");                      
            
            in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());
            
            // Generate keys
            System.out.println("Server generating keys...");            
            algorithmRSAController = new AlgorithmRSAController(1024, PUBLIC_KEY_FILE, PRIVATE_KEY_FILE);
            algorithmRSAController.generateKeysToFile();            
            System.out.println("Server generated...");            
            // Send and receive public key to server
            sendPublicKeyToServer(algorithmRSAController.getPublicKey().getEncoded());
            receivePublicKeyFromServer(in.readLine());
            
            algorithmRSAModel = new AlgorithmRSAModel();
        }
        catch(IOException e) {
            System.out.println("Error: " + e);
        } catch (Exception ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void readMessageFromServer (String message) {
        try {
            if(!message.equals("") && message!=null) {
                this.message_from_server = message;
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
                this.message_to_server = algorithmRSAModel.encrypt(message);
                System.out.println(message_to_server);
                out.writeBytes(message_to_server + "\n");
                out.flush();                
            }
            else {                
                this.message_to_server = algorithmRSAModel.encrypt("null");
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
    
    public void receivePublicKeyFromServer(String message) {                                    
        try {
            byte[] bytes = Base64.getDecoder().decode(message);
            algorithmRSAController.writeToFile(PUBLIC_KEY_FILE, bytes);
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendPublicKeyToServer(byte[] bytes) {
        try {            
            String message = Base64.getEncoder().encodeToString(bytes);
            out.writeBytes(message + "\n");
            out.flush();
        } catch (IOException ex) {
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
