package com.example.awplay;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Sender extends Activity {
    // 布局文件代码省略,因为没有用到布局文件的内容
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 连接网络的部分放在子线程中，防止主线程阻塞
        new Thread() {
            public void run() {
                try {
                    // 建立连接到远程服务器的Socket
                    // 服务器ip要么是公网ip，要么是和你在一个局域网下的服务器的局域网ip地址
                    Socket socket = new Socket("10.60.221.88", 40000);
                    // Socket对应的输出流
                    OutputStream os = socket.getOutputStream();
                    // 向socket另一端发送一句
                    String message = "测试测试！";
                    os.write(message.getBytes("utf-8"));
					Log.e("向服务器发送消息", message);

                    // 关闭socket
					socket.close();
                }catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
