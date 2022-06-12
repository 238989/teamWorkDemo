/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 23898
 */
public class FileWork implements Runnable{

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(9821);
            // 采用循环不断的接收来自客户端的请求
            while (true) {
                try (Socket socket = ss.accept();
                        DataInputStream inputStream = new DataInputStream(socket.getInputStream());) {
//                     先读长度
                    long len = inputStream.readLong();
                    Date date = new Date();//获取当前的日期
                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");//设置日期格式
                    String name = df.format(date);//获取String类型的时间

                    byte[] bytes = new byte[(int) len];
                    // 再读这个长度的字节到字节数组
                    inputStream.readFully(bytes);
                    // 将读到的内容保存为文件到本地
                    File file = new File("E:\\" + name + ".png");
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(bytes);
                    // 接收成功 在/data/local/tmp/下面可以看到生成的png格式图片
                    System.out.println("Reading...OK");
//                    ss.close();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(MessageWork.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
