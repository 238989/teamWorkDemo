package Dao;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Sender {

    public static void main(String[] args) throws IOException {
        // 创建一个ServerSocket 用于监听客户端Socket的连接请求
        ServerSocket ss = new ServerSocket(30000);
        // 采用循环不断的接收来自客户端的请求
        while (true) {
            // 每当接收到客户端Socket的请求，服务端也对应产生一个Socket
            Socket s = ss.accept();
            // 获得 socket对端的输出流
            OutputStream os = s.getOutputStream();
            // 向socket另一端发送一句 Hello
            os.write("TestTest!\n".getBytes("utf-8"));
            // 关闭输出流,关闭socket
            os.close();
            s.close();
        }
    }
}
