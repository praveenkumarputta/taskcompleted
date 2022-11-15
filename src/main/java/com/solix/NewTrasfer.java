// Praveen

package com.solix;

//import java.lang.annotation.Target;
import java.util.Properties;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class NewTrasfer {// TransferFiles class name

	public static void main(String[] args) throws Exception { // Main method
		String sourceip = null;
		String targetip = null;
		String tagetpath = null;
		String targetuser = null;
		String targetpwd = null;
		String sourceuser = null;
		String sourcepwd = null;
		String sourcepath = null;
		String port = null;
		Session sourceSession = null; // Session is class name and sourceSession is its object
		Session targetSession = null; // Session is class name and sourceSession is its object
		// String localTempPath = null;// String localTempPath variable

		// This are soure inputs
		sourceip = "13.59.35.124"; //naresh
		sourcepath = "/home/naveen/java/helloword.java";
		sourceuser = "naveen";
		sourcepwd = "naveen@01";

		// This are target inputs siri
		targetip = "18.188.216.136";
		tagetpath = "/home/praveen1/java/";
		targetuser = "praveen1";
		targetpwd = "praveen@01";

		// localTempPath = "C:\\WIP\\temp\\";
		ChannelSftp sourcesftpChannel = null; // sourcesftpChannel is the object of chanel ChannelSftp
		ChannelSftp targetftpChannel = null;
		port = "22";// port 22 for SSh secure shell Connection

		try {
			sourceSession = createSFTPSession(sourceuser, sourceip, sourcepwd, port);
			// here we are creating connection with source
			sourcesftpChannel = (ChannelSftp) sourceSession.openChannel("sftp");
			sourcesftpChannel.connect(); // connect() is the method from Channel class used to establish connection

			targetSession = createSFTPSession(targetuser, targetip, targetpwd, port);
			targetftpChannel = (ChannelSftp) targetSession.openChannel("sftp");
			targetftpChannel.connect();
			System.out.println(targetftpChannel.toString());

			// uploadFile(targetftpChannel,targetSession,sourcepath,tagetpath);
			//targetftpChannel.put(sourcepath, tagetpath + "new.txt");
			//sourcesftpChannel.put(sourcepath, tagetpath + "new.txt");
			sourcesftpChannel.get(remotePath+remoteFileName,localPath+remoteFileName);// downloading
			System.out.println(targetftpChannel.toString());

			upload(targetftpChannel, sourcepath, tagetpath);
			
//			nareshSftpChannel.put(localPath + remoteFileName, nareshTempPath + remoteFileName);   //naresh path sirish 
			System.out.println("Uploaded to Naresh.."); // session id creation or not showing this line

		}

		catch (Exception e) {// if

		} finally {
			sourcesftpChannel.disconnect();// its disconnect() channel
			sourceSession.disconnect();// its close session
		}

//		public void uploadFile() throws JSchException, SftpException {
//		      //  System.out.printf("Uploading [%s] to [%s]...%n", localPath, remotePath);
//		        if (targetftpChannel == null) {
//		            throw new IllegalArgumentException("Connection is not available");
//		        }
//		        targetftpChannel.put(sourcepath,tagetpath);
//		    }

	}

	public static void upload(ChannelSftp targetftpChannel,String sourcepath,String tagetpath) throws JSchException, SftpException {
	    ChannelSftp channelSftp = targetftpChannel;
	    channelSftp.connect();
	 
//	    String localFile = "src/main/resources/sample.txt";
//	    String remoteDir = "remote_sftp_test/";
	 
	    channelSftp.put(sourcepath, tagetpath + "my.java");
	 
	    channelSftp.exit();
	    
	    
	 // use the get method , if you are using android remember to remove "file://" and use only the relative path
	   //  sftp.rm("/var/www/remote/myfile.txt");
	    
	   // channelSftp.rm(sourcepath);
	}

//	public static void uploadFile(ChannelSftp targetftpChannel,Session targetSession,String sourcepath,String tagetpath)  throws JSchException, SftpException {
//		ChannelSftp channel = targetftpChannel;
//		Session session = targetSession;
//		if (channel == null) {
//            throw new IllegalArgumentException("Connection is not available");
//        }
//		System.out.println(channel.toString());
//        channel.put(sourcepath,tagetpath+"new.txt");
//        
//        channel.disconnect();// its  disconnect() channel
//		session.disconnect();// its close session
//    }	

	private static Session createSFTPSession(final String user, final String host, final String pass,
			final String port) {
		Session session = null;
		try {
			final int pt = Integer.parseInt(port);
			final Properties config = new Properties();// config is the Properties class Object
			config.put("StrictHostKeyChecking", "no");// put(k=v)
			final JSch jsch = new JSch();
			session = jsch.getSession(user, host, pt);// this getSession() session object with host user
														// and port detail.
			session.setPassword(pass);
			session.setConfig(config);
			session.connect();
			System.out.println("Session created and connected Successfully : " + user);
		} catch (Exception e) {
			System.out.println(("Exception in createSession() ::" + e.getMessage()));
		}
		return session;
	}
}
