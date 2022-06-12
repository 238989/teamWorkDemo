/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

/**
 *
 * @author 23898
 */
public class AirDrop {
    
    public static void main(String[] args) {
        MessageWork mes = new MessageWork();
        FileWork file = new FileWork();
        SendMes send = new SendMes();
        Thread t1 = new Thread(mes);
        Thread t2 = new Thread(file);
        Thread sendMessage = new Thread(send);
        sendMessage.start();
        t1.start();
        t2.start();
    }

}
