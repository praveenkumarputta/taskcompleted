package com.solix;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileLength {
    public static void main(String agrs[]) {
        Path path = null;
        double fileSize=0 ;
        long bytes = 0;
        String filename ="itextpdf-5.4.0.jar";
        String filepath = "/home/bhushanam/jarfiles/";
        File file = new File(filepath+filename);
        System.out.println(file);
        path = Paths.get(filepath +filename);
       System.out.println("sdsdfsd"+path);
        //file=new File(filepath);
        if (file.exists()) {
            fileSize =  file.length() / (1024 * 1024);
            //fileSize=(fileSize);
            System.out.println("FileS in loop::"+fileSize);
            
            try {
                bytes = Files.size(path);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("NIO " + bytes%(1024 * 1024));
            
             //file.delete();
        //System.out.println("FileSize::"+fileSize);
    }
    }
}