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

/**
 *
 * @author Administrator
 */
public class Receiver {

    public static void main(String[] args) throws IOException {
        // 创建一个ServerSocket 用于监听客户端Socket的连接请求
        ServerSocket ss = new ServerSocket(40000);
        // 采用循环不断的接收来自客户端的请求
        while (true) {
            Socket s = ss.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String content = br.readLine();
            System.out.println("收到服务器消息" + content);
            br.close();
            s.close();
        }
    }
}
