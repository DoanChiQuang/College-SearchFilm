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
import java.net.ServerSocket;
import java.net.Socket;

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
            
            while(!message_from_client.equals("bye") && message_from_client!=null)
            {                
                try {
                    this.message_from_client = in.readLine();
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
        }
    }        
}
