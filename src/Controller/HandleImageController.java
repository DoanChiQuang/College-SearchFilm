/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ij.IJ;
import ij.ImagePlus;
import ij.io.FileSaver;

public class HandleImageController {

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
            return "Kiểu File trùng. Vui lòng chọn kiểu khác!";
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
                file.setJpegQuality(100);                
                String newPath = s + "." + extensionType;
                file.saveAsJpeg(newPath);
                return newPath;
            }
            catch(Exception ex) {
                System.out.println("Lỗi! Không lưu được ảnh!");
            }
        }
        return "Lỗi! Chuyển đổi không thành công!";
    }

//    public static void main(String[] args) {
//        HandleImageController h = new HandleImageController();        
//        String path=".\\src\\assets\\image\\film_DoctorStange.jpg";
//        String kieu_moi="png";
//        System.out.println("path moi: "+h.formatFileToNewFile(path,kieu_moi));
//    }
}
