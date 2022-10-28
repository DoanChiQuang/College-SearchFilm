package Controllerr;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class HandleImageController implements Runnable {
	
	private Thread thread;					//Thread
	private String namethread;				//Tên thread
	public BufferedReader 	in;				//read from pipe socket
	public BufferedWriter 	out;			//write to pipe
	public ServerSocket 	listen; 		//Socket Communicate
	public Socket			socket_server;
	public BufferedImage buffered;
	//Socket in server
	
	
	//Constructor
	public HandleImageController ()
	{
		this.in  		   = null;
		this.out 		   = null;
		this.listen 	   = null;
		this.socket_server = null;
	}
		
		public Image resize( URL url, String type) throws IOException {
				url = new URL("https://www.purina.co.uk/sites/default/files/2020-11/Working%20Dogs%20Everything%20You%20Need%20to%20KnowTEASER.jpeg"); 
				final BufferedImage image = ImageIO.read(url);
				Image dimg = null;
				int width = image.getWidth();
				int height = image.getHeight();
					if(type.equals("small"))				
					{	
						while(width>100)
						{
							width -= 50;
						}
						while(height>100)
						{
							height -= 50;
						}
						 dimg  = image.getScaledInstance(width, height,Image.SCALE_SMOOTH);
						
					}					
					if(type.equals("medium"))
					{	
						while(width>150)
						{
							width -= 100;
						}
						while(height>150)
						{
							height -= 100;
						}
						
						 dimg  = image.getScaledInstance(width, height,Image.SCALE_SMOOTH);
					}
					if(type.equals("large"))
					{	
						while(width>400)
						{
							width += 300;
						}
						while(height>400)
						{
							height += 100;
						}
						
						 dimg  = image.getScaledInstance(width, height,Image.SCALE_SMOOTH);					
					}
					return dimg;	
		}
		
		public Image gray( URL url) throws IOException {
			url = new URL("https://www.purina.co.uk/sites/default/files/2020-11/Working%20Dogs%20Everything%20You%20Need%20to%20KnowTEASER.jpeg"); 
			final BufferedImage image = ImageIO.read(url);
			int width = image.getWidth();
			int height = image.getHeight();
			// convert to grayscale 
	        for (int y = 0; y < height; y++) { 
	            for (int x = 0; x < width; x++) { 
	                
	                // Here (x,y)denotes the coordinate of image 
	                // for modifying the pixel value. 
	                int p = image.getRGB(x, y); 
	  
	                int a = (p >> 24) & 0xff; 
	                int r = (p >> 16) & 0xff; 
	                int g = (p >> 8) & 0xff; 
	                int b = p & 0xff; 
	  
	                // calculate average 
	                int avg = (r + g + b) / 3; 
	  
	                // replace RGB value with avg 
	                p = (a << 24) | (avg << 16) | (avg << 8) 
	                    | avg; 
	  
	                 image.setRGB(x, y, p); 
	            } 
	        }
			return image; 
					
	}
	

//		public ArrayList<BufferedImage> Search_Image() throws IOException{
//
//			String token = "";
//	        String projectId = "0";
//	        String image_url = "";
//	        String[] labels = {"label1", "label2"};
//	        int limit = 10;
//	        double threshold = 0;
//
//	        StringBuilder paramsString = new StringBuilder();
//	        for (String label : labels) {
//	            paramsString.append("&labels=").append(label);
//	        }
//	        paramsString.append("&and=true"); // to use 'or' operator for filtering labels remove this line or change it to '&and=false'
//	        paramsString.append("&limit=").append(limit);
//	        paramsString.append("&threshold=").append(threshold);
//
//	        String body = "{\r\n    \"url\": \"%s\"\r\n}".formatted(image_url);
//
//	        URL url = new URL("https://platform.sentisight.ai/api/similarity?project=" + projectId + paramsString);
//	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//	        connection.setRequestProperty("Content-Type", "application/json");
//	        connection.setRequestProperty("X-Auth-token", token);
//	        connection.setRequestMethod("POST");
//	        connection.setDoOutput(true);
//	        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
//	        wr.writeBytes(body);
//	        wr.flush();
//	        wr.close();
//
//	        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//	        String output;
//	        while ((output = in.readLine()) != null) {
//	            System.out.println(output);
//	        }
//	        in.close();
//
//			return null;
//
//		}
	public static void main(String[] args) {
		HandleImageController a = new HandleImageController();
//		a.Open_Server(6000);		
	}

	public void start() {
		System.out.println("Start thread:"+this.namethread);
		if(thread == null)
		{
			thread = new Thread(this, namethread);			
			thread.start();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
