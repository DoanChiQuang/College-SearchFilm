/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Controller.ClientHandlerController;
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
public class ServerMain {

    public static int count = 0;
    
    public static void main(String[] args) throws IOException {
        ServerSocket server_socket = new ServerSocket(5056);
        System.out.println("Server started"); 
        System.out.println("Waiting for a client ...");
        
        while(true) {
            Socket socket = null;
            try {
                socket = server_socket.accept();
                count++;
                System.out.println("A new client accepted: " + socket);
                System.out.println("----------------------------------------------");
                
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());               
                Thread t = new ClientHandlerController(socket, in, out);
                t.start();                
            } catch (Exception e) {
                socket.close();
                e.printStackTrace();
            }
        }
    }
    
}
