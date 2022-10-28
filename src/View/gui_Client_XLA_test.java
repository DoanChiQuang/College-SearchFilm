package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.model_Image;
import Controllerr.HandleImageController;

public class gui_Client_XLA_test extends JFrame {
	
	private JPanel contentPane;
	private JButton gray, resize,upload,getimage,api;
	private String path = "null";
	private URL url ;
	private String keyword = "null";
	private String extension = "null";
	private model_Image model_image = new model_Image();
	private HandleImageController handleImageController = new HandleImageController();
	private JLabel image;
	public boolean click = false;
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				JFrame.setDefaultLookAndFeelDecorated(true);
			}
		});
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					gui_Client_XLA_test frame = new gui_Client_XLA_test();
					frame.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public gui_Client_XLA_test() throws MalformedURLException {
		setBounds(100, 100, 1091, 663);
		setLocationRelativeTo(null);
        setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 1077, 56);
		contentPane.add(panel);
		panel.setLayout(null);
		
		getimage = new JButton("GET IMAGE");
		getimage.setBounds(5, 5, 520, 45);
		panel.add(getimage);
		
		//Phần giao diện ảnh - bên trái						
				image = new JLabel();
				image.setBounds(10, 65, 710, 520);		
				//panelanh.add(image);
				this.add(image);
				
				
				
		//Phần chức năng
				JPanel panel_1 = new JPanel();
				panel_1.setBounds(728, 65, 354, 520);
//				panel_1.setBorder(title);
				contentPane.add(panel_1);		
				panel_1.setLayout(null);
				
				gray = new JButton("GrayScale");			
				gray.setBounds(22, 245, 322, 39);
				panel_1.add(gray);
				
				resize = new JButton("Resize");
				resize.setBounds(22, 321, 322, 39);
				panel_1.add(resize);
				
				api = new JButton("API");
				api.setBounds(22,460 , 322, 39);
				panel_1.add(api);
				
				this.Function_Gray();
				this.Function_Resize();
//				this.Function_Upload();
				this.Function_Get_Image();
				
	}
	
		public void Function_Get_Image() throws MalformedURLException {
			 url = new URL("https://www.purina.co.uk/sites/default/files/2020-11/Working%20Dogs%20Everything%20You%20Need%20to%20KnowTEASER.jpeg"); 
		
			getimage.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
		            BufferedImage temp2;	
		            try {
		            	temp2 = ImageIO.read(url);
						Image dimg = temp2.getScaledInstance(image.getWidth(), image.getHeight(),
							        Image.SCALE_SMOOTH);
						image.setIcon(new ImageIcon(dimg));
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		          
				}
				});	
			
			
		}
	
	//Hàm GrayScale (thang màu xám) cho ảnh
	public void Function_Gray() {
			gray.addActionListener(new ActionListener() {		
				@Override
				public void actionPerformed(ActionEvent e) {				
					if(click==true)
					{
						JOptionPane.showMessageDialog(null,"Lỗi!Vui lòng lưu ảnh trước!","Lỗi",JOptionPane.CANCEL_OPTION);
						return;
					}
//					
					//Check đã upload ảnh chưa
					if(url.equals("null"))
					{
						JOptionPane.showMessageDialog(null,"Lỗi!Vui lòng upload ảnh!","Lỗi",JOptionPane.CANCEL_OPTION);
						return;
					}
					 Image result = null;
					try {
						result = handleImageController.gray(url);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//					model_Image result = MainView.controller.Receive_Image();
					if(result == null)
					{
						JOptionPane.showMessageDialog(null,"Lỗi!Ảnh không thể chuyển!","Lỗi",JOptionPane.CANCEL_OPTION);
						return;
					}		
					Image dimg = result.getScaledInstance(image.getWidth(), image.getHeight(),
						        Image.SCALE_SMOOTH);
					image.setIcon(new ImageIcon(dimg));			  
					click = true;
				}

			});
		}

		//Hàm resize ảnh theo 3 button small - medium - large
	public void Function_Resize() {
			resize.addActionListener(new ActionListener() {		
				@Override
				public void actionPerformed(ActionEvent e) {	
					if(click==true)
					{
						JOptionPane.showMessageDialog(null,"Lỗi!Vui lòng lưu ảnh trước!","Lỗi",JOptionPane.CANCEL_OPTION);
						return;
					}
					//Check đã upload ảnh chưa

					if(url.toString().equals("null"))
					{
						JOptionPane.showMessageDialog(null,"Lỗi!Vui lòng upload ảnh!","Lỗi",JOptionPane.CANCEL_OPTION);
						return;
					}
					Object[] option = {"Small","Medium","Large"};
					int choose = JOptionPane.showOptionDialog(null,"Chọn định dạng ảnh","Định dạng", 
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, null);				
					if(choose == -1)
						return;					
					//nhận từ server
					
					Image result = null;
					try {
						result = handleImageController.resize(url, "small");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(result == null)
					{
						JOptionPane.showMessageDialog(null,"Lỗi!Ảnh không thể chuyển!","Lỗi",JOptionPane.CANCEL_OPTION);
						return;
					}		
					
							
//					Image dimg = result.getScaledInstance(image.getWidth(), image.getHeight(),
//						        Image.SCALE_DEFAULT);
					image.setIcon(new ImageIcon(result));	
					click = true;
				}
			});		
		}

	
//	//hàm dùng để get các ảnh tương tự về
//		public void Function_API()
//		{
//			api.addActionListener(new ActionListener() {		
//				@Override
//				public void actionPerformed(ActionEvent e) {
////					client_Controller controller = new client_Controller();
//					ArrayList<BufferedImage> result = null;
//					try {
//						result = server_Controller.Search_Image();
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}				
//					if(result == null)
//					{
//						JOptionPane.showMessageDialog(null,"Lỗi!Ảnh không thể chuyển!","Lỗi",JOptionPane.CANCEL_OPTION);
//						return;
//					}	
//					System.out.println("Get ảnh về thành công");
//					
//					Object[] option = {"Có","Không"};
//					int choose = JOptionPane.showOptionDialog(null,"Bạn muốn xem ảnh không?","Kết quả thành công", 
//							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, null);
//					if(choose == 0)
//					{
//						for (int i = 0; i < result.size(); i ++) {
//				            JFrame frame = new JFrame();
//				            BufferedImage test = result.get(i);
//				            frame.setSize(test.getWidth(), test.getHeight());
//				            JLabel label = new JLabel();			            
//				            label.setSize(test.getWidth(), test.getHeight());
//				            Image dimg = result.get(i).getScaledInstance(label.getWidth(), label.getHeight(),
//							        Image.SCALE_SMOOTH);
//				            label.setIcon(new ImageIcon(dimg));		
//				            frame.add(label);
//				            frame.setVisible(true);
//				        }
//					}
//					if(choose == 1)
//						return;
//				}
//			});
//		}
}
