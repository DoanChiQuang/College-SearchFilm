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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chiquang
 */
public class ServerController {
    private Socket socket                   = null;
    private ServerSocket server_socket      = null;
    private BufferedReader in               = null;
    private DataOutputStream out            = null;
    protected String message_from_client    = "";
    protected String message_to_client      = "";
    public static String PUBLIC_KEY_FILE    = "./KEYRSA/publicKeyServer.txt";
    public static String PRIVATE_KEY_FILE   = "./KEYRSA/privateKeyServer.txt";
    AlgorithmRSAModel algorithmRSAModel           = null;
    AlgorithmRSAController algorithmRSAController = null;
     
    public ServerController(int port)
    {        
        try
        {
            server_socket = new ServerSocket(port);
            System.out.println("Server started"); 
            System.out.println("Waiting for a client ...");
 
            socket = server_socket.accept();
            System.out.println("Client accepted");
            
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());                        
            
            // Generate keys
            System.out.println("Client generating keys...");            
            algorithmRSAController = new AlgorithmRSAController(1024, PUBLIC_KEY_FILE, PRIVATE_KEY_FILE);
            algorithmRSAController.generateKeysToFile();            
            System.out.println("Client generated...");            
            // Send and receive key to client
            receivePublicKeyFromClient(in.readLine());
            sendPublicKeyToClient(algorithmRSAController.getPublicKey().getEncoded());                                    
            
            algorithmRSAModel = new AlgorithmRSAModel();
            
            while(!message_from_client.equals("bye") && message_from_client!=null)
            {                
                try {
                    readMessageFromClient(algorithmRSAModel.decrypt(in.readLine()));
                    System.out.println(message_from_client);
                }
                catch (Exception e) {
                    System.out.println("Error: " + e);
                }
            }
            System.out.println("Closing connection");
 
            // Close connection
            socket.close();
            in.close();
        }
        catch(IOException e)
        {
            System.out.println("Error: " + e);
        } catch (Exception ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void readMessageFromClient (String message) {
        try {
            if(!message.equals("") && message!=null) {                
                this.message_from_client = message;
            }
            else {
                this.message_from_client = "null";
            }
        } 
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    public void writeMessageToClient(String message) {
        try {                        
            if(!message.equals("") && message!=null) {
                this.message_to_client = algorithmRSAModel.encrypt(message);
                System.out.println(message_to_client);
                out.writeBytes(message_to_client + "\n");
                out.flush();
            }
            else {
                this.message_to_client = algorithmRSAModel.encrypt("null");
                out.writeBytes(message_to_client + "\n");
                out.flush();
            }
        } 
        catch (IOException e) {
            System.out.println("Error: " + e);
        } catch (Exception ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public void receivePublicKeyFromClient(String message) {                    
        try {
            byte[] bytes = Base64.getDecoder().decode(message);
            algorithmRSAController.writeToFile(PUBLIC_KEY_FILE, bytes);
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendPublicKeyToClient(byte[] bytes) {
        try {            
            String message = Base64.getEncoder().encodeToString(bytes);            
            out.writeBytes(message + "\n");
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
