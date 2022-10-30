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
    private String ip                    = "127.0.0.1";
    private int port                     = 5000;    
    
    public void connect() {
        try {
            socket = new Socket(ip, port);
            System.out.println("Connected!");
            
            in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());
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
                out.writeBytes(message_to_server + "\n");
                out.flush();
            }
            else {
                this.message_to_server = "null";
                out.writeBytes(message_to_server + "\n");
                out.flush();
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
}
