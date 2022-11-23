/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ij.IJ;
import ij.ImagePlus;
import ij.io.FileSaver;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class HandleImageController {
    
    public String handleImage(String path, String action, String extension, String option, String folderName) throws IOException, JSONException {
        String pathName = saveFile(path, extension, folderName);
        String newPath = "";
        if(!pathName.equals("")) {
            switch(action) {
                case "compress":
                    newPath = compress(pathName);
                    return newPath;
                case "changeextension":
                    newPath = formatFileToNewFile(pathName, option);
                    return newPath;
                case "zoom":
                    newPath = zoom(pathName, option);
                    return newPath;
                case "gray":
                    newPath = grayScale(pathName);
                    return newPath;
//                case "sameimage":
                    
                case "identify":
                    File deleteFile = new File(pathName);
                    deleteFile.delete();
                    String npath = saveFilePath(path, extension);
                    String rootPath = new File("").getAbsolutePath();
                    String aPath = rootPath + npath.substring(1);
                    DetectionController detectionController = new DetectionController(aPath);
                    Mat img = detectionController.detectObjectOnImage();
                    LocalDateTime myDateObj = LocalDateTime.now();
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
                    String formattedDate = myDateObj.format(myFormatObj);
                    String pathFile = ".\\src\\assets\\image_server\\" + formattedDate + "." + extension;
                    Imgcodecs imageCodecs = new Imgcodecs();
                    imageCodecs.imwrite(pathFile, img);
                    return "Success;" + pathFile;
                default:
                    return "Error;null";
            }
        }
        else {
            System.out.println("File null!");
        }
        return "Error;null";
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
            File file = new File(path);
            file.delete();
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
                File file = new File(path);
                file.delete();
                return "Error;Lỗi không lưu được file!";
            }
        }        
    }
    
    public String compress(String path) {
        ImagePlus img = IJ.openImage(path);
        FileSaver file = new FileSaver(img);
        file.setJpegQuality(10);
        String ext = getExtensionFile(path);
        String path_save = "";
        for(int i=path.length()-1; i>=0; i--) {
            if(path.charAt(i)=='.') {
                for(int j=0; j<i; j++){
                    path_save+=String.valueOf(path.charAt(j));
                }
            }
        }
        path_save = path_save + "." + ext;
        try {
            if(ext.equals("png"))
            {
                file.saveAsJpeg(path_save);
                return "Success;" + path_save;
            }
            if(ext.equals("jpg"))
            {		        	                    
                file.saveAsJpeg(path_save);
                return "Success;" + path_save;
            }
            if(ext.equals("tif"))
            {		        	                    
                file.saveAsJpeg(path_save);                        
                return "Success;" + path_save;
            }
            if(ext.equals("gif"))
            {		        	
                file.saveAsJpeg(path_save);                        
                return "Success;" + path_save;
            }
        } 
        catch (Exception e) {
            return "Error;Lỗi không lưu được file!";
        }
        return "Error;Lỗi không lưu được file";
    }
    
    public String saveFilePath(String path, String extensionType) {
        String newPath = "";
        try {
            byte[] bytes = Base64.getMimeDecoder().decode(path);
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
            String formattedDate = myDateObj.format(myFormatObj);            
            newPath = ".\\darknet\\data\\" + formattedDate + "." + extensionType;
            FileUtils.writeByteArrayToFile(new File(newPath), bytes);
        } catch (IOException ex) {
            Logger.getLogger(HandleImageController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return newPath;
    }
    
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
    
    public String zoom(String path, String type) throws IOException {        
        String ext = getExtensionFile(path);
        BufferedImage originalImage = ImageIO.read(new File(path));
        int height = originalImage.getHeight();
        int width = originalImage.getWidth();
        System.out.println(path);
        if(type.equals("small")) {
            while(width>100) width -= 50;
            while(height>100) height -= 50;
            BufferedImage resizeImageJpg = resizeImage(originalImage, width, height);
            ImageIO.write(resizeImageJpg, ext, new File(path));
            return "Success;" + path;
        }
        if(type.equals("medium")) {            
            BufferedImage resizeImageJpg = resizeImage(originalImage, originalImage.getWidth(), originalImage.getHeight());
            ImageIO.write(resizeImageJpg, ext, new File(path));
            return "Success;" + path;
        }
        if(type.equals("large")) {
            if(height < 150) height = height + 100;
            if(width < 150) width = width + 100;
            BufferedImage resizeImageJpg = resizeImage(originalImage, originalImage.getWidth()+200, originalImage.getHeight()+200);
            ImageIO.write(resizeImageJpg, ext, new File(path));
            return "Success;" + path;
        }
        return "Error;Lỗi không xử lý được hình ảnh!";
    }
    
    public String grayScale(String path) {
        try {                   
            String ext = getExtensionFile(path);
            BufferedImage originalImage = ImageIO.read(new File(path));
            int height = originalImage.getHeight();
            int width = originalImage.getWidth();        
            for (int y = 0; y < height; y++) { 
                for (int x = 0; x < width; x++) {                 
                    int p = originalImage.getRGB(x, y); 
                    int a = (p >> 24) & 0xff; 
                    int r = (p >> 16) & 0xff; 
                    int g = (p >> 8) & 0xff; 
                    int b = p & 0xff; 

                    int avg = (r + g + b) / 3; 

                    p = (a << 24) | (avg << 16) | (avg << 8) 
                        | avg; 

                    originalImage.setRGB(x, y, p);
                }
            }
            ImageIO.write(originalImage, ext, new File(path));
            return "Success;" + path;
        }
        catch (Exception e) {
            return "Error;Lỗi không xử lý được hình ảnh!";
        }
    }        
    
    public static BufferedImage resizeImage(BufferedImage originalImage, int IMG_WIDTH, int IMG_HEIGHT) {
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();
        return resizedImage;
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
