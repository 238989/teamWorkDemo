package com.example.awplay;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import android.util.Config;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Test extends Activity {
    // 布局文件代码省略,因为没有用到布局文件的内容
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 连接网络的部分放在子线程中，防止主线程阻塞
        new Thread() {
            public void run() {
                Socket socket;
                try {
                    // 创建一个Socket对象，并指定服务端的IP及端口号
                    socket = new Socket("10.63.114.153", 9821);

                    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                    //发送的图片为demo.jpg，将bitmap转为字节数组
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.test);
                    ByteArrayOutputStream bout = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,bout);
                    //写入字节的长度，再写入图片的字节
                    long len = bout.size();
                    //这里打印一下发送的长度
                    Log.i("sendImgMsg", "len: "+len);
                    outputStream.writeLong(len);
                    outputStream.write(bout.toByteArray());
                    //发送成功
                    Log.i("ServerReceviedByTcp","outputStream.write ok" );

                    // 发送读取的数据到服务端
                    outputStream.flush();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
}
