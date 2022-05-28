package com.example.awplay;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receiver extends Activity {
    // 布局文件代码省略,因为没有用到布局文件的内容
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 连接网络的部分放在子线程中，防止主线程阻塞
        new Thread() {
            public void run() {
                try {
                    // 建立连接到远程服务器的Socket
                    // 服务器ip要么是公网ip 要么是和你在一个局域网下的服务器的局域网ip地址
                    Socket socket = new Socket("10.60.221.88", 30000);
                    // 将Socket对应的输入流包装为BufferedReader
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String content = br.readLine();
                    // 这里注意，如果你想把你收到的数据，显示到界面上，在这里不能直接操作的
                    // 因为Android子线程中是不能更新ui的，需要在主线程中建立Handler，然后向handler发送message才可以
					Log.e("收到服务器消息", content);
                    // 关闭输出流，关闭socket
                    br.close();
					socket.close();
                }catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
