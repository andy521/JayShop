package controller;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/12/25.
 */
public class FtpController {
    @Test
    public void testFtp(){
        try {
            FTPClient ftpClient =new FTPClient();
            ftpClient.connect("192.168.136.129",21);
            ftpClient.login("uftp","jaycekong824");
            FileInputStream fileInputStream = new FileInputStream(new File("D:/123.jpg"));
            File file = new File("D:/hello.jpg");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            int i = 0;
            while((i = fileInputStream.read())!=-1) {
                fileOutputStream.write(i);
            }
            ftpClient.changeWorkingDirectory("/home/uftp/images/");
            boolean flag = ftpClient.storeFile("hello.jpg",fileInputStream);
            System.out.println(flag);
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        boolean success = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect("192.168.136.129", 21);//连接FTP服务器
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login("uftp", "jaycekong824");//登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return ;
            }
            ftp.changeWorkingDirectory("/home/uftp/images/");
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            FileInputStream fileInputStream = new FileInputStream(new File("D:/123.jpg"));
            ftp.storeFile("hello.jpg", fileInputStream);

            fileInputStream.close();
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
    }
}
