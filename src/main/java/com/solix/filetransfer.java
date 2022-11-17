
// Praveen

	package com.solix;

	//import java.lang.annotation.Target;
	import java.util.Properties;
import java.util.Scanner;
import java.util.Vector;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
	import com.jcraft.jsch.JSchException;
	import com.jcraft.jsch.Session;
	import com.jcraft.jsch.SftpException;

	public class filetransfer {// TransferFiles class name

		public static void main(String[] args) throws Exception { // Main method
			String sourceip = null;
			String sourceuser = null;
			String sourcepwd = null;
			String sourcepath = null;
			String port = null;
			String searchKey =null;
			
			String localPath = "D:\\sftp\\";
			
			Session sourceSession = null; // Session is class name and sourceSession is its object
			Session targetSession = null; // Session is class name and sourceSession is its object
			// String localTempPath = null;// String localTempPath variable

			// This are soure inputs
			sourceip = "18.191.193.238"; //naresh
			sourcepath ="/home/ubuntu/csvfiles/";
			sourceuser = "ubuntu";
			sourcepwd = "teja";
			

			
			ChannelSftp sourcesftpChannel = null; // sourcesftpChannel is the object of chanel ChannelSftp
			port = "22";// port 22 for SSh secure shell Connection

			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter String To Search : ");
			searchKey = scanner.next();
			try {
				
				sourceSession = createSFTPSession(sourceuser, sourceip, sourcepwd, port);
				// here we are creating connection with source
				sourcesftpChannel = (ChannelSftp) sourceSession.openChannel("sftp");
				sourcesftpChannel.connect(); // connect() is the method from Channel class used to establish connection
				
				sourcesftpChannel.cd(sourcepath);
				@SuppressWarnings("unchecked")
				Vector<ChannelSftp.LsEntry> list = sourcesftpChannel.ls("*"+searchKey+"*.*");
				
				if(list.size() != 0) {
				
				for(ChannelSftp.LsEntry entry:list) {
					sourcesftpChannel.get(sourcepath+entry.getFilename(),localPath);
				}
				System.out.println("copied successfully");
				  }
				else {
					System.out.println("no files found");
				}
				
			}
		

			catch (Exception e) {// if

			}
			finally {
				sourcesftpChannel.disconnect();// its disconnect() channel
				sourceSession.disconnect();// its close session
			}
		}

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


