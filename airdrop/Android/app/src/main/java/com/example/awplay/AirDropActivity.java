package com.example.awplay;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class AirDropActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AirDropActivity";
    private TextView chose,showMes;
    Button sendMes;
    String filePath,mes;
    Uri uri;
    private ClipboardManager mClipboard = null;
    String ip = "192.168.149.104";          // 服务端IP地址

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_drop);
        showMes = findViewById(R.id.show_mes);
        sendMes = findViewById(R.id.send_mes);

        //选择文件
        chose = findViewById(R.id.btn_choose_file);
        chose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
//                intent.setType("*/*");     //不限制文件类型    //                intent.setType("application/pdf");    //pdf文件类型
//                intent.setType("application/vnd.ms-powerpoint");   //ppt文件类型
                intent.setType("image/*");                 //图片文件类型
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 1);
            }
        });

        //发送剪切板内容
        sendMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取到剪切版内容
                mes = getClipboardContent().trim();
                mes = mes.replaceAll("\n"," ");
                //显示内容
                showMes.setText(mes);
                //发送内容
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Socket socket;
                        try{
                            // 建立连接到远程服务器的Socket
                            socket = new Socket(ip, 9822);
                            // Socket对应的输出流
                            OutputStream os = socket.getOutputStream();
                            // 向socket另一端发送一句
                            os.write(mes.getBytes("utf-8"));
                            Log.e("向服务器发送消息", mes);

                            // 关闭socket
                            socket.close();
                        }catch(IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

    }
    //发送文件
    @Override
    public void onClick(View v) {
        new Thread() {
            public void run() {
                Socket socket;
                try {
                    // 创建一个Socket对象，并指定服务端的IP及端口号
                    socket = new Socket(ip, 9821);
                    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                    //发送的图片为demo.jpg，将bitmap转为字节数组
                    Bitmap bitmap = getBitmapFromUri(uri);
                    ByteArrayOutputStream bout = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,bout);
                    //写入字节的长度，再写入图片的字节
                    long len = bout.size();
                    //这里打印一下发送的长度
                    Log.i(TAG, "send len: "+len);
                    outputStream.writeLong(len);
                    outputStream.write(bout.toByteArray());
                    //发送成功
                    Log.i(TAG,"outputStream.write ok" );
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

    //获取文件路径
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            // 用户未选择任何文件，直接返回
            return;
        }
        uri = data.getData(); // 获取用户选择文件的URI
        Log.i(TAG, "onActivityResult: 获取到的文件名为："+uri);
        // 通过ContentProvider查询文件路径
        ContentResolver resolver = this.getContentResolver();
        Cursor cursor = resolver.query(uri, null, null, null, null);
        if (cursor == null) {
            // 未查询到，说明为普通文件，可直接通过URI获取文件路径
            filePath = uri.getPath();
            return;
        }
        if (cursor.moveToFirst()) {
            // 多媒体文件，从数据库中获取文件的真实路径
            filePath = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
            chose.setText(filePath);
            Log.i(TAG, "onActivityResult: 获取到的文件名为："+filePath);
        }
        cursor.close();
    }


    //将Uri转为Bitmap文件
    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    /**
     * 获取剪切板上的内容
     */
    private String getClipboardContent() {
        // Gets a handle to the clipboard service.
        if (null == mClipboard) {
            mClipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        }
        String resultString = "";
        // 检查剪贴板是否有内容
        if (!mClipboard.hasPrimaryClip()) {
            Toast.makeText(AirDropActivity.this, "Clipboard is empty", Toast.LENGTH_SHORT).show();
        } else {
            ClipData clipData = mClipboard.getPrimaryClip();
            int count = clipData.getItemCount();
            for (int i = 0; i < count; ++i) {
                ClipData.Item item = clipData.getItemAt(i);
                CharSequence str = item.coerceToText(AirDropActivity.this);
                Log.i("mengdd", "item : " + i + ": " + str);
                resultString += str;
            }
        }
        return resultString;
    }

}
