//package controllerr;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.net.Socket;
//import java.util.ArrayList;
//
//
//import Model.model_Image;
//
//public class client_Controller {
//
//	private Socket socket;
//	private BufferedReader in;
//	private BufferedWriter out= null;
//	private String key;
//	
//	//Hàm để nhận về dữ liệu các ảnh khi chạy chức năng API (tìm ảnh tương tự)
//	public ArrayList<model_Image> Receive_API(){
//		String receive = "";
//		ArrayList<model_Image> array = new ArrayList<model_Image>();
//					
//		while(true) {
//			try {				
//				receive = in.readLine();
////				receive = this.decrypt_string(receive);
//				if(receive.equals("end"))
//					return array;
//				if(receive.equals("fail_input"))
//					return null;
//				//Đọc và gắn vào Model Image
//				String image = receive;			
//				model_Image temp = new model_Image();	
//				temp.convert_to_image(image);				
//				temp.extension = "jpg";			
//				array.add(temp);
//			} catch (IOException e) {
//				return null;
//			}
//		}		
//	}
//	
//	public static void main(String[] args) throws IOException, InterruptedException {
//	}
//}
