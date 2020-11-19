package Net04;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
* 服务器端, 接收客户端的数据, 并发送反馈
* */
public class ServerDemo {
    public static void main(String[] args) throws IOException {
//        创建服务器端的Socket
        ServerSocket ss = new ServerSocket(10000);
//        监听客户端对象, 返回一个Socket对象
        Socket s = ss.accept();
//        获取输入流, 读数据, 并把数据显示在控制台
        InputStream is = s.getInputStream();
        byte[] bys = new byte[1024];
        int len = is.read(bys);
        String data = new String(bys, 0, len);
        System.out.println("服务器: " + data);
//        给出反馈
        OutputStream os = s.getOutputStream();
        os.write("数据已经收到".getBytes());
//        释放资源
        ss.close();

    }
}
