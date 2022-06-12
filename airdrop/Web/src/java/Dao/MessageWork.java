/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 23898
 */
public class MessageWork implements Runnable {

    @Override
    public void run() {
        try {
           
            ServerSocket ss = new ServerSocket(9822);
            // 采用循环不断的接收来自客户端的请求
            while (true) {
                try (Socket s = ss.accept(); BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
                    String content = br.readLine();
                    System.out.println("收到服务器消息：" + content);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(MessageWork.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
