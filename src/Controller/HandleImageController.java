/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ij.IJ;
import ij.ImagePlus;
import ij.io.FileSaver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

public class HandleImageController {
    
    public String handleImage(String path, String action, String extension, String option, String folderName) {
        String pathName = saveFile(path, extension, folderName);
        String newPath = "";
        if(!pathName.equals("")) {
            switch(action) {
                case "zip":
                    // code block
                    break;            
                case "changeextension":                                        
                    newPath = formatFileToNewFile(pathName, option);
                    return newPath;
                case "zoom":
                    // code block
                    break;                        
                default:
                    // code block
            }
        }
        else {
            System.out.println("File null!");
        }
        return newPath;
    }
    // Lấy đuôi file
    public String getExtensionFile(String path){
        String s = "";
        for(int i=path.length()-1; i>=0; i--){
            if(path.charAt(i)=='.'){
                //lay ten file
                while(true) {
                    i++;
                    try {
                        s+=path.charAt(i);
                    }
                    catch(Exception ex) {
                        break;
                    }
                }
                break;             
            }
        }        
        return s;
    }
    
    // Chuyển đổi hình ảnh
    public String formatFileToNewFile(String path, String extensionType){
        String s = "";
        if(this.getExtensionFile(path).equals(extensionType)) {
            return "Error;Lỗi trùng đuôi file!";
        }
        else {
            for(int i=path.length()-1; i>=0; i--) {
                if(path.charAt(i)=='.') {
                    for(int j=0; j<i; j++){
                        s+=String.valueOf(path.charAt(j));
                    }
                }
            }
            try {                                 
                ImagePlus image = IJ.openImage(path);
                FileSaver file = new FileSaver(image);
                File deleteFile = new File(path);
                file.setJpegQuality(100);
                String newPath = s + "." + extensionType;
                file.saveAsJpeg(newPath);
                deleteFile.delete();
                return "Success;" + newPath;
            }
            catch(Exception ex) {
                System.out.println("Error;Lỗi! Không lưu được ảnh!");
                return "Error;Lỗi không lưu được file!";
            }
        }        
    }
    
//    public String Compress(String pathInput){                        
//        ImagePlus img = IJ.openImage(pathInput);                                
//        FileSaver file = new FileSaver(img);
//        file.setJpegQuality(10);
//        
//        try{
//            file.saveAsJpeg(path_save);
//        }catch(Exception ex){
//            System.out.print("Khong tim thay folder luu anh compress");
//        }	        
//            if(type_file(pathInput).equals("png"))
//            {		        	
//                    try{
//                        file.saveAsJpeg(path_save);
//                        System.out.println("Da nen vao folder: ");
//                        return path_save;
//                        }catch(Exception ex){
//                            System.out.print("Khong tim thay folder luu anh compress");
//                        }
//            }
//            if(type_file(pathInput).equals("jpg"))
//            {		        	
//                    try{
//                        file.saveAsJpeg(path_save);
//                        System.out.println("Da nen vao folder: ");
//                        return path_save;
//                        }catch(Exception ex){
//                            System.out.print("Khong tim thay folder luu anh compress");
//                        }
//            }
//            if(type_file(pathInput).equals("tif"))
//            {		        	
//                    try{
//                        file.saveAsJpeg(path_save);
//                        System.out.println("Da nen vao folder: ");
//                        return path_save;
//                        }catch(Exception ex){
//                            System.out.print("Khong tim thay folder luu anh compress");
//                        }
//            }
//            if(type_file(pathInput).equals("gif"))
//            {		        	
//                    try{
//                        file.saveAsJpeg(path_save);
//                        System.out.println("Da nen vao folder: ");
//                        return path_save;
//                        }catch(Exception ex){
//                            System.out.print("Khong tim thay folder luu anh compress");
//                        }
//            }
//            return "khong co path_save";		        			      	
//    }
    
    public String saveFile(String path, String extensionType, String folderName) {
        String newPath = "";
        try {
            byte[] bytes = Base64.getMimeDecoder().decode(path);
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
            String formattedDate = myDateObj.format(myFormatObj);
            System.out.println("Date: " + formattedDate);
            newPath = ".\\src\\assets\\" + folderName +"\\" + formattedDate + "." + extensionType;
            FileUtils.writeByteArrayToFile(new File(newPath), bytes);            
        } catch (IOException ex) {
            Logger.getLogger(HandleImageController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return newPath;
    }
    
    public String convertFileToString(String path) {
        String encodedfile = null;
        try {            
            File file = new File(path);
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = new String(Base64.getEncoder().encode(bytes));
            fileInputStreamReader.close();
        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
        return encodedfile;
    }

//    Main Test
//    public static void main(String[] args) {
//        HandleImageController h = new HandleImageController();        
//        String path=".\\src\\assets\\image\\film_DoctorStange.jpg";
//        String kieu_moi="png";
//        System.out.println("path moi: "+h.formatFileToNewFile(path,kieu_moi));
//    }
}
