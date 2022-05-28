package Dao;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Test {
    
    public static void main(String[] args) {
        createServerRecevied();
    }

    public static void createServerRecevied() {

        // 声明一个ServerSocket对象
        ServerSocket serverSocket = null;

        try {
            // 创建一个ServerSocket对象，并让这个Socket在9821端口监听
            serverSocket = new ServerSocket(9821);
            // 调用ServerSocket的accept()方法接受客户端所发送的请求，
            // 如果客户端没有发送数据，那么该线程就停滞不继续
            Socket socket = serverSocket.accept();

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());

            long len = inputStream.readLong();
            System.out.println("len = " + len);
            byte[] bytes = new byte[(int) len];

            inputStream.readFully(bytes);
            // 将读到的内容保存为文件到本地
            File file = new File("web\\Receiver\\" + len + ".png");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
            // 接收成功
            System.out.println("Reading...OK");
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
