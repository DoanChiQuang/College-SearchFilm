/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AlgorithmRSAModel;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Chiquang
 */
public class ClientHandlerController extends Thread {
    private Socket socket                           = null;    
    private BufferedReader in                       = null;
    private DataOutputStream out                    = null;
    protected String message_from_client            = "";
    protected String message_to_client              = "";
    protected String action                         = "";
    protected String extension                      = "";
    protected String option                         = "";
    AlgorithmRSAModel algorithmRSAModel             = null;
    AlgorithmRSAController algorithmRSAController   = null;
    private AlgorithmAESController aes;	
    private HandleImageController handleImageController;    
     
    public ClientHandlerController(Socket socket, BufferedReader in, DataOutputStream out) {
        this.socket = socket;
        this.in = in;
        this.out = out;
    }
    
    @Override
    public void run() {
        try {                    
            handShakeSSL();

            while(!action.equals("bye"))
            {
                try {
                    handleImageController = new HandleImageController();
                    readMessageFromClient(in.readLine());
                    System.out.println("Data: " + message_from_client);
                    System.out.println("Action: " + action);
                    System.out.println("Extension: " + extension);
                    System.out.println("Option: " + option);
                    if(!extension.equals("null")) {
                        // Handle image
                        String result = handleImageController.handleImage(message_from_client, action, extension, option, "image_server");
                        System.out.println("Xử lý ảnh: " + result);
                        handelResultImage(result);
                    }
                    else {
                        if(!action.equals("bye")) {
                            // Handle search film
                            String result = APIController.handleCallAPI(message_from_client,option);                        
                            System.out.println("Xử lý phim: " + result);
                            handleResultSearch(result);
                        }                        
                    }
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
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }       
    
    public void handShakeSSL() {
        try {
            // Generate keys
            System.out.println("Server generating keys...");            
            algorithmRSAController = new AlgorithmRSAController(1024);
            algorithmRSAController.generateKeysToFile();
            System.out.println("Server generated...");
            
            // Read request connect
            String requestKey = in.readLine();
            System.out.println("Client want to request key...");
            
            // Send public key
            algorithmRSAModel = new AlgorithmRSAModel();
            algorithmRSAModel.publickey = algorithmRSAModel.getPublicKey();
            algorithmRSAModel.privatekey = algorithmRSAModel.getPrivateKey();
            out.writeBytes(algorithmRSAModel.getPublicKey_ToString() + "\n");
            out.flush();
            
            // Receive secret key
            String secretKey = in.readLine();
            aes = new AlgorithmAESController();
            aes.mykey = algorithmRSAModel.decrypt(secretKey);
            System.out.println("Successfully for handshake ssl: " + aes.mykey);
            System.out.println("----------------------------------------------");
                        
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }        
    }
    
    public void handelResultImage(String result) {
        String temp[] = result.split(";");
        String status = temp[0];
        String file = temp[1];
        if(status.equals("Success")) {
            String encode = handleImageController.convertFileToString(file);
            String extension = handleImageController.getExtensionFile(file);
            String message = status + ";" + encode + ";" + extension + ";image";
            writeMessageToClient(message);
            File deleteFile = new File(file);                     
            deleteFile.delete();
        }
        else if(status.equals("Error")) {
            writeMessageToClient(result + ";null;image");
        }
    }
    
    public void handleResultSearch(String result) {
        String temp[] = result.split(";");
        String status = temp[0];
        String jsonString = temp[1];
        if(status.equals("Success")) {            
            String message = status + ";" + jsonString + ";null;search";
            writeMessageToClient(message);
        }
        else if(status.equals("Error")) {
            writeMessageToClient(result + ";null;search");
        }
    }
    
    public void readMessageFromClient (String message) {
        try {
            String decodeMessage = aes.decrypt_String(message, aes.mykey);
            if(!decodeMessage.equals("") && decodeMessage!=null) {
                String[] temp = decodeMessage.split(";");
                this.message_from_client = temp[0];
                this.action = temp[1];
                this.extension = temp[2];
                this.option = temp[3];
            }
            else {
                this.message_from_client = "null";
                this.action = "null";
                this.extension = "null";
                this.option = "null";
            }
        } 
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    public void writeMessageToClient(String message) {
        try {                        
            if(!message.equals("") && message!=null) {
                this.message_to_client = aes.encrypt_String(message, aes.mykey);
                System.out.println("Server say: " + message_to_client);
                out.writeBytes(message_to_client + "\n");
                out.flush();
            }
            else {
                this.message_to_client = aes.encrypt_String("null", aes.mykey);
                System.out.println("Server say: " + message_to_client);
                out.writeBytes(message_to_client + "\n");
                out.flush();
            }
        }
        catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
