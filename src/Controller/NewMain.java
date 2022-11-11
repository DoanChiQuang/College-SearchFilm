/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chiquang
 */
import ij.IJ;
import ij.ImagePlus;
import ij.io.FileSaver;


public class NewMain {
    
    public String path_img_add_compress(String location_path){
        int acsii; 
        String s=""; //bien chua path tu 0-> \ cuoi
        String s1=""; //bien chua path tu \ cuoi -> het chuoi (ten file)
        String s2="";  //ten folder se them vao path
        for(int i=location_path.length()-1;i>=0;i--){
            acsii=(int) location_path.charAt(i);  //convert char tai i -> ma acsii
            if(acsii==92) {  //neu ma acsii = 92 ( dau '\')
                s=location_path.substring(0, i+1);  //bo chuoi tu 0->i vao s
                while(true){  //lay ten file
                    i++;
                    try{
                      s1+=location_path.charAt(i);  
                    }catch(Exception ex){
                        break;
                    }                 
                }
                break;             
                }
            }
       return s+s2+s1;  //chuoi dau + ten folder + ten file
    }
    public String type_file(String path){
        String s="";
        for(int i=path.length()-1;i>=0;i--){
            if(path.charAt(i)=='.'){
                while(true){  //lay ten file
                    i++;
                    try{
                      s+=path.charAt(i);  
                    }catch(Exception ex){
                        break;
                    }                 
                }
                break;             
                }
            }
        return s;
    }
    public String Compress(String pathInput){
        
        
        //chuyen duong dan anh thanh imagePlus
        ImagePlus img = IJ.openImage(pathInput);
        
        String path_save=path_img_add_compress(pathInput);
        
        //khai bao file save, chinh chat luong anh
        FileSaver file = new FileSaver(img);
        file.setJpegQuality(10); //chinh chat luong anh
                          
        try{
            file.saveAsJpeg(path_save);
        }catch(Exception ex){
            System.out.print("Khong tim thay folder luu anh compress");
        }
	
        
                        if(type_file(pathInput).equals("png"))
		        {		        	
		        	try{
                                    file.saveAsJpeg(path_save);
                                    System.out.println("Da nen vao folder: ");
                                    return path_save;
                                    }catch(Exception ex){
                                        System.out.print("Khong tim thay folder luu anh compress");
                                    }
		        }
                        if(type_file(pathInput).equals("jpg"))
		        {		        	
		        	try{
                                    file.saveAsJpeg(path_save);
                                    System.out.println("Da nen vao folder: ");
                                    return path_save;
                                    }catch(Exception ex){
                                        System.out.print("Khong tim thay folder luu anh compress");
                                    }
		        }
                        if(type_file(pathInput).equals("tif"))
		        {		        	
		        	try{
                                    file.saveAsJpeg(path_save);
                                    System.out.println("Da nen vao folder: ");
                                    return path_save;
                                    }catch(Exception ex){
                                        System.out.print("Khong tim thay folder luu anh compress");
                                    }
		        }
                        if(type_file(pathInput).equals("gif"))
		        {		        	
		        	try{
                                    file.saveAsJpeg(path_save);
                                    System.out.println("Da nen vao folder: ");
                                    return path_save;
                                    }catch(Exception ex){
                                        System.out.print("Khong tim thay folder luu anh compress");
                                    }
		        }
                        return "khong co path_save";
		        			      
			
  }
    public static void main(String[] args)  {
        
        String path=".\\src\\assets\\image_server\\film_DoctorStange.jpg";
        
        NewMain a =new NewMain();
        System.out.println(a.Compress(path));
  
}
}


