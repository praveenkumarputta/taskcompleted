// Sirisha target

package com.solix;

//import java.lang.annotation.Target;
import java.util.Properties;
import java.util.Scanner;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class NewTrasfer2 {// TransferFiles class name

	public static void main(String[] args) throws Exception { // Main method
		String myIp = null;
		String myTempPath = null;
		String myUser = null;
		String myPwd = null;
		String remoteip = null;
		String nareshIp = null;
		String nareshTempPath = null;
		String nareshUser = null;
		String nareshPwd = null;
		String remoteUser = null;
		String remotePwd = null;
		String remotePath = null;
		String port = null;
		Session sourceSession = null; // Session is class name and sourceSession is its object
		String localPath = null;
		String localFileName = null;
		String remoteFileName = null;

		String nareshFileName = null;

		Session nareshSession = null; // Session is class name and sourceSession is its object
		// String localTempPath = null;// String localTempPath variable
		Session mySession = null; // localSystem..........

		// This are soure inputs
		remoteip = "18.188.216.136";
		remotePath = "/home/praveen1/java/"; // remote location//sirisha file name source
		localPath = "D:\\sftp\\";// temp file my code save
		nareshTempPath = "/home/naveen/workspace/";// Naresh is targer
		remoteUser = "praveen1";
		remotePwd = "praveen@01";

		myIp = "192.168.1.67";
		myUser = "praveen";
		myPwd = "Nallappa@01";
		myTempPath = "/mnt/d/sftp/";

		Scanner scr = new Scanner(System.in);
		System.out.println("EnterRemoteFileName : ");
		remoteFileName = scr.next();

		// System.out.println("EnterLocalFileName : ");
		// localFileName = scr.next();

		// System.out.println("Enter NareshFileName : ");
		// nareshFileName = scr.next();

		// This are remote naresh inputs
		nareshIp = "13.59.35.124";
		nareshUser = "naveen";
		nareshPwd = "naveen@01";

		// localTempPath = "C:\\WIP\\temp\\";
		ChannelSftp sourcesftpChannel = null; // sourcesftpChannel is the object of chanel ChannelSftp
		ChannelSftp nareshSftpChannel = null;
		ChannelSftp mySftpChannel = null;
		port = "22";// port 22 for SSh secure shell Connection

		try {
			sourceSession = createSFTPSession(remoteUser, remoteip, remotePwd, port);
			// here we are creating connection with source
			sourcesftpChannel = (ChannelSftp) sourceSession.openChannel("sftp");
			sourcesftpChannel.connect(); // connect() is the method from Channel class used to establish connection
			
			nareshSession = createSFTPSession(nareshUser, nareshIp, nareshPwd, port);
			nareshSftpChannel = (ChannelSftp) nareshSession.openChannel("sftp");
			nareshSftpChannel.connect();
			
			mySession = createSFTPSession(myUser, myIp, myPwd, port);
			mySftpChannel = (ChannelSftp) mySession.openChannel("sftp");
			mySftpChannel.connect();

			// sourcesftpChannel.put(localPath + localFileName, remotePath +
			// remoteFileName); // uploading
			sourcesftpChannel.get(remotePath+remoteFileName,localPath+remoteFileName);// downloading
			// sourcesftpChannel.rm(remotePath);//deleting
			System.out.println("Downloded From Sirisha.."); // session id creation or not showing this line

			

			nareshSftpChannel.put(localPath + remoteFileName, nareshTempPath + remoteFileName);
			System.out.println("Uploaded to Naresh.."); // session id creation or not showing this line

			// nareshSftpChannel.rm(nareshTempPath + nareshFileName); // deleteing a file
			// from naresh

			//System.out.println("Deleted Form Naresh.."); // session id creation or not showing this line

			
			mySftpChannel.rm(myTempPath+remoteFileName);
			System.out.println("Deleted Form MySystem......");
		}

		catch (Exception e) {// if

		} finally {
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
