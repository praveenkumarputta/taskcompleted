//package com.solix;
//
//
//import com.jcraft.jsch.*;
//
//import java.util.Properties;
//import java.util.Vector;
//
///**
// * A simple SFTP client using JSCH http://www.jcraft.com/jsch/
// */
//public final class SftpClient {
//    private final String      host ="192.168.1.67";
//    private final int         port =22;
//    private final String      username1 ="praveen";
//    private final JSch        jsch;
//    private       ChannelSftp channel;
//    private       Session     session;
//    jsch          = new JSch();
//
//    /**
//     * @param host     remote host
//     * @param port     remote port
//     * @param username remote username
//     */
////    public SftpClient(String host, int port, String username) {
////        this.host     = host;
////        this.port     = port;
////        this.username = username;
//       
////    }
//
//    /**
//     * Use default port 22
//     *
//     * @param host     remote host
//     * @param username username on host
//     */
////    public SftpClient(String host, String username) {
////        this("192.168.1.67", 22, "praveen");
////    }
//
//    /**
//     * Authenticate with remote using password
//     *
//     * @param password password of remote
//     * @throws JSchException If there is problem with credentials or connection
//     */
//    public void authPassword() throws JSchException {
//        session = jsch.getSession(username1, host, port);
//        //disable known hosts checking
//        //if you want to set knows hosts file You can set with jsch.setKnownHosts("path to known hosts file");
//        Properties config = new Properties();
//        config.put("StrictHostKeyChecking", "no");
//        session.setConfig(config);
//        session.setPassword("Nallappa@01");
//        session.connect();
//        channel = (ChannelSftp) session.openChannel("sftp");
//        channel.connect();
//    }
//    
//    
//    private String remoteHost = "HOST_NAME_HERE";
//    private String username = "USERNAME_HERE";
//    private String password = "PASSWORD_HERE";
//  
//    
//    
//    public void uploadFile() throws JSchException, SftpException {
//      //  System.out.printf("Uploading [%s] to [%s]...%n", localPath, remotePath);
//        if (channel == null) {
//            throw new IllegalArgumentException("Connection is not available");
//        }
//        channel.put("C:\\Users\\A\\Desktop\\praveen.txt", "/home/praveen/java/");
//    }
//
//
////    public void authKey(String keyPath, String pass) throws JSchException {
////        jsch.addIdentity(keyPath, pass);
////        session = jsch.getSession(username, host, port);
////        //disable known hosts checking
////        //if you want to set knows hosts file You can set with jsch.setKnownHosts("path to known hosts file");
////        var config = new Properties();
////        config.put("StrictHostKeyChecking", "no");
////        session.setConfig(config);
////        session.connect();
////        channel = (ChannelSftp) session.openChannel("sftp");
////        channel.connect();
////    }
//    
//    public void close() {
//        if (channel != null) {
//            channel.exit();
//        }
//        if (session != null && session.isConnected()) {
//            session.disconnect();
//        }
//    }
//}
//
//
