// Sirisha target

package com.solix;

import java.util.Properties;

import com.jcraft.jsch.*;

public class NewTrasfer3 {

	private static final String REMOTE_HOST = "18.188.216.136";
	private static final String USERNAME = "praveen1";
	private static final String PASSWORD = "praveen@01";
	private static final int REMOTE_PORT = 22;
	private static final int SESSION_TIMEOUT = 10000;
	private static final int CHANNEL_TIMEOUT = 5000;

	public static void main(String[] args) {

		String localFile = "C:\\Users\\A\\Desktop\\";
		String remoteFile = "/home/praveen1/java/naveen.txt";

		Session jschSession = null;

		try {
			final Properties config = new Properties();// config is the Properties class Object
			config.put("StrictHostKeyChecking", "no");// put(k=v)
			JSch jsch = new JSch();
			// jsch.setKnownHosts("/home/mkyong/.ssh/known_hosts");
			jschSession = jsch.getSession(USERNAME, REMOTE_HOST, REMOTE_PORT);

			// authenticate using private key
			// jsch.addIdentity("/home/mkyong/.ssh/id_rsa");

			// authenticate using password
			jschSession.setPassword(PASSWORD);
			jschSession.setConfig(config);

			// 10 seconds session timeout
			jschSession.connect(SESSION_TIMEOUT);

			Channel sftp = jschSession.openChannel("sftp");

			// 5 seconds timeout
			sftp.connect(CHANNEL_TIMEOUT);

			ChannelSftp channelSftp = (ChannelSftp) sftp;

			// transfer file from local to remote server
			//channelSftp.put(localFile, remoteFile);

			 channelSftp.get(remoteFile,localFile);
			channelSftp.rm(remoteFile);
			
			// download file from remote server to local
			// channelSftp.get(remoteFile, localFile);

			channelSftp.exit();

		} catch (JSchException | SftpException e) {

			e.printStackTrace();

		} finally {
			if (jschSession != null) {
				jschSession.disconnect();
			}
		}

		System.out.println("Done");
	}

}