package com.solix;

import java.io.File;      
import java.io.FileInputStream;      
import java.io.FileOutputStream;       
import java.io.ObjectInputFilter.Config;       
import java.util.ArrayList;             
import java.util.List;                 
import java.util.Properties;      

import javax.naming.spi.DirStateFactory.Result;   

import com.jcraft.jsch.ChannelSftp;  
import com.jcraft.jsch.JSch;  
import com.jcraft.jsch.JSchException;   
import com.jcraft.jsch.Session;   


public class TransferFiles {

    public static void main(String[] args) throws Exception {
        String sourceip = null;
        String targetip = null;
        String tagetpath = null;
        String targetuser = null;
        String targetpwd = null;
        String sourceuser = null;
        String sourcepwd = null;
        String sourcepath = null;
        String port = "22";
        Session sourceSession = null;
        Session targetSession = null;
        //String localTempPath = null;
        FileOutputStream fos = null;

     // This are soure inputs
     		sourceip = "192.168.1.67";
     		sourcepath = "/home/praveen/sapArchiving/sapArching.txt";
     		sourceuser = "praveen";
     		sourcepwd = "Nallappa@01";

     		// This are target inputs
     		targetip = "192.168.1.67";
     		tagetpath = "/home/praveen/java/";
     		targetuser = "praveen";
     		targetpwd = "Nallappa@01";

        //localTempPath = "/home/naresh/movefiles/naresh.txt";
                ChannelSftp sourcesftpChannel = null;
                ChannelSftp targetftpChannel = null;
                port = "22";
        
        try {
        	Session session = null;
        	final int pt = Integer.parseInt(port);
			final Properties config = new Properties();// config is the Properties class Object
			config.put("StrictHostKeyChecking", "no");// put(k=v)
			final JSch jsch = new JSch();
			session = jsch.getSession(sourceuser, sourceip, pt);// this getSession() session object with host user
														// and port detail.
			session.setPassword(sourcepwd);
			session.setConfig(config);
			session.connect();
			System.out.println("Session created and connected Successfully");
		
            
            sourceSession = createSFTPSession(sourceuser, sourceip, sourcepwd, port);
            sourcesftpChannel = (ChannelSftp) sourceSession.openChannel("sftp");
            sourcesftpChannel.connect();
            
            targetSession = createSFTPSession(targetuser, targetip, targetpwd, port);
            targetftpChannel = (ChannelSftp) targetSession.openChannel("sftp");
            targetftpChannel.connect();
            
            
            /*
             * JSch jsch = new JSch(); jsch.setKnownHosts(
             * "/home/praveen/sapArichiveMoveFiles/sapArichiveMoveFiles.txt"); Session
             * jschSession = jsch.getSession(sourceuser, sourcepath);
             * jschSession.setPassword(sourcepwd); jschSession.connect();
             */
                
                
//                fos = new FileOutputStream("sapArichiveMoveFiles.txt");
//                sourcesftpChannel.cd(sourcepath);
//                sourcesftpChannel.get("sapArichiveMoveFiles.txt", fos);
//                fos.flush(); 
//                
                
//                File srcFile = new File("sapArichiveMoveFiles.txt");
//                targetftpChannel.cd(tagetpath);
//                FileInputStream srcFIS = new FileInputStream(srcFile);
//                targetftpChannel.put(srcFIS, srcFile.getName());
//                
//                System.out.println("file transfered...");

        } catch (Exception e) {
            System.out.println(e);
        
            
        } finally {
            /*
             * sourcesftpChannel.disconnect(); sourceSession.disconnect();
             * targetftpChannel.disconnect(); targetftpChannel.disconnect();
             */
        }

    }
    /*
     * file from source 
     *                      fos = new FileOutputStream(sapArichiveMoveFiles.txt);                  
     *                      channelSftp.cd(sourcepath);                                    
     *                       channelSftp.get(sapArichiveMoveFiles.txt, fos);
     *                     
     *                         fos.flush(); 
     * 
     *                       onto target channelSftpTgt.cd(targetDir); 
     *  
     *                            FileInputStream srcFIS = new FileInputStream(sapArichiveMoveFiles.tx);
     *                                                            
     *                          channelSftpTgt.put(srcFIS, srcFile.getName());
     */

    private static Session createSFTPSession(final String user, final String host, final String pass,
            final String port) {

        Session session11 = null;
        try {
            final int pt = Integer.parseInt(port);
           // final Properties config = new Properties();
            JSch jsch1 = new JSch();
            
            session11 = jsch1.getSession("praveen", "praveen", pt);
            session11.setPassword("Nallappa@01");
            session11.setConfig("StrictHostKeyChecking", "no");
            session11.connect();
            ChannelSftp channel = null;
            channel = (ChannelSftp) session11.openChannel("sftp");
            channel.connect();
            
            //fos = new FileOutputStream(sapArichiveMoveFiles.txt);
            
            
            /*
             * File localFile = new File("localfilepath"); channel.put(new
             * FileInputStream(localFile), localFile.getName()); channel.disconnect();
             * session11.disconnect();
             */
            System.out.println("Session created and connected Successfully");
        } catch (Exception e) {
            System.out.println(("Exception in createSession() ::" + e.getMessage()));
        }
        return session11;
    }
}