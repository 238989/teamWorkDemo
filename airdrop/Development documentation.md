## 单元测试
由于尚未完成整体的软件开发，本实验在开发过程中只针对Android端与PC端之间的通信问题、获取Android端剪切板内容的功能进行了单元测试，以及对目前软件的目标功能测试。    


## 持续集成
在目前阶段，我们通过在github中添加.yml文件进行持续集成，使用持续集成操作快速发现Bug并进行更正。在后期通过学习更多的actions相关用法后，实现使用持续集成生成APK文件，进行相应的测试。

## 关键代码
### PC终端
接收图片：

        // 声明一个ServerSocket对象
        ServerSocket serverSocket = null;
        try {
            // 创建一个ServerSocket对象，并让这个Socket在9821端口监听
            serverSocket = new ServerSocket(9822);

            // 采用循环不断的接收来自客户端的请求
            while (true) {
                // 调用ServerSocket的accept()方法接受客户端所发送的请求，
                // 如果客户端没有发送数据，那么该线程就停滞不继续
                Socket socket = serverSocket.accept();

                DataInputStream inputStream = new DataInputStream(socket.getInputStream());

                long len = inputStream.readLong();
                System.out.println("len = " + len);
                byte[] bytes = new byte[(int) len];

                inputStream.readFully(bytes);
                // 将读到的内容保存为文件到本地
                File file = new File("web\\Receiver\\" + len + ".png");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(bytes);
                // 接收成功
                System.out.println("Reading...OK");
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

接收文字：

        try {
            // 创建一个ServerSocket 用于监听客户端Socket的连接请求
            ServerSocket ss = new ServerSocket(9821);
            // 采用循环不断的接收来自客户端的请求
            while (true) {
                Socket s = ss.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String content = br.readLine();
                System.out.println("收到服务器消息" + content);
                br.close();
                s.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

发送文字：

        try {
            // 创建一个ServerSocket 用于监听客户端Socket的连接请求
             ServerSocket ss = new ServerSocket(30000);
        // 采用循环不断的接收来自客户端的请求
            while (true) {
             // 每当接收到客户端Socket的请求，服务端也对应产生一个Socket
                 Socket s = ss.accept();
                 // 处理控制台输入并得到响应的类型数据
                 Scanner sc = new Scanner(System.in);
                 // 获得socket对端的输出流
                 OutputStream os = s.getOutputStream();
                 // 向socket另一端发送字符串
                 os.write(sc.nextLine().getBytes("utf-8"));
                 // 关闭输出流，关闭socket
                os.close();
                 s.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

### Android终端：
获取系统图片文件：

     protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            // 用户未选择任何文件，直接返回
            return;
        }
        uri = data.getData(); // 获取用户选择文件的URI
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
        }
        cursor.close();
    }

将URI转为图片： 

    //将Uri转为Bitmap文件
    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }


发送图片： 

    socket = new Socket(ip, 9821);
    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
    //发送的图片为demo.jpg，将bitmap转为字节数组
    Bitmap bitmap = getBitmapFromUri(uri);
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.PNG,100,bout);
    //写入字节的长度，再写入图片的字节
    long len = bout.size();
    outputStream.writeLong(len);
    outputStream.write(bout.toByteArray());
    outputStream.flush();

获取剪切板的内容：

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

发送文字：

    Socket socket;
    try{
        socket = new Socket(ip, 9822);
        OutputStream os = socket.getOutputStream();
        os.write(mes.getBytes("utf-8"));
        socket.close();
    }catch(IOException e) {
        e.printStackTrace();
    }
