/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 23898
 */
public class SendMes implements Runnable{
    @Override
    public void run() {
        while(true){
            Socket socket;
            try {
                // 建立连接到远程服务器的Socket
                socket = new Socket("10.63.95.249", 9823);
                // Socket对应的输出流
                OutputStream os = socket.getOutputStream();
                Scanner sc = new Scanner(System.in);
                String mes = sc.nextLine();
                // 向socket另一端发送一句
                os.write(mes.getBytes("utf-8"));
                System.out.println("success send mes!");
                // 关闭socket
                socket.close();
            } catch (IOException e) {
            }
        }
        
    }
}
