/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Chiquang
 */
public class ClientController {
    private Socket socket                = null;
    private BufferedReader in            = null;
    private DataOutputStream out         = null;
    protected String message_from_server = "";
    protected String message_to_server   = "";
    
    public ClientController(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            System.out.println("Connected!");
            
            in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());
            writeMessageToServer("bye");
            closeConnect();
        }
        catch(IOException e) {
            System.out.println("Error: " + e);
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
            System.out.println(message);
            if(!message.equals("") && message!=null) {
                this.message_to_server = message;
                out.writeBytes(message);
            }
            else {
                this.message_to_server = "null";
                out.writeBytes("null");
            }
        } 
        catch (IOException e) {
            System.out.println("Error: " + e);
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
    
    public void run() {
        ClientController client = new ClientController("127.0.0.1", 5000);
    }
    
//    public static void main(String[] args) {
//        ClientController client = new ClientController("127.0.0.1", 5000);
//    }
}
