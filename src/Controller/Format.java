/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ij.IJ;
import ij.ImagePlus;
import ij.io.FileSaver;

public class Format {


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
    public String doi_duoi(String path,String kieu_file_moi){
        Format b=new Format();
        String s="";
        if(b.type_file(path).equals(kieu_file_moi)){
            return "Kieu file duoc giu nguyen, vui long doi sang kieu khac";
        }
        else{
                for(int i=path.length()-1;i>=0;i--){
                    if(path.charAt(i)=='.'){
                        for(int j=0;j<i;j++){
                            s+=String.valueOf(path.charAt(j));
                        }
                        try{
                            //mo anh
                                ImagePlus img = IJ.openImage(path);
                                FileSaver file = new FileSaver(img);
                                file.setJpegQuality(100);
                                file.saveAsJpeg(s+"."+kieu_file_moi);
                                return s+"."+kieu_file_moi;
                            }catch(Exception ex){
                                System.out.println("Loi!!,khong the doi duoi");
                            }       
                    }       
                }
             }
        return "loi!!, khong chay dong nao";
    }
    public static void main(String args[]) {
        Format a=new Format();
        String path=".\\src\\img\\a.jpg";
        String kieu_moi="png";
        System.out.println("path moi: "+a.doi_duoi(path,kieu_moi));
    }
}
