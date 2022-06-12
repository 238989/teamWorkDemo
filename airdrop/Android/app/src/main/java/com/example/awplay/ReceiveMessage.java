package com.example.awplay;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReceiveMessage implements Runnable {

    private String receiveMes;

    public String getReceiveMes() {
        return receiveMes;
    }

    @Override
    public void run() {
        try {

            ServerSocket ss = new ServerSocket(9823);
            // 采用循环不断的接收来自客户端的请求
            while (true) {
//                Socket socket = new Socket("10.63.95.249", 30000);
                Socket s = ss.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String content = br.readLine();
                System.out.println("收到服务器消息" + content);
                br.close();
                s.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ReceiveMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
