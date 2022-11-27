package com.solix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FilesToZip {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Scanner sn = new Scanner(System.in);
		String path = "D:\\sftp\\";
		System.out.println("enter zipfile name");
		String zipfile = sn.next();
		//Creating a File object for directory
			      File directoryPath = new File(path);
	   //List of all files and directories
			    String[] filePaths = directoryPath.list();
//			    for(int i=0 ; i<filePaths.length;i++) {
//			    	System.out.println(filePaths[i]);
//			    }
		String zipPath =  "D:\\sftp\\"+zipfile+".zip";
		
		try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipPath))) {
		    for (String filePath : filePaths) {
		        File fileToZip = new File(path+filePath);
		        zipOut.putNextEntry(new ZipEntry(fileToZip.getName()));
		        Files.copy(fileToZip.toPath(), zipOut);
		        fileToZip.delete();
		    }
		    System.out.println(zipfile+".zip created successfully");
		}
		catch (Exception e) {
			System.out.println(e);
		}
		sn.close();
	}
}
