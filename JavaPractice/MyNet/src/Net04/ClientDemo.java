package Net04;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/*
* TCP通信试验, 客户端, 向服务器发送数据, 并接收反馈
* */
public class ClientDemo {
    public static void main(String[] args) throws IOException {
//        创建Socket对象
        Socket s = new Socket("10.177.119.249", 10000);
//        获取输出流, 写数据
        OutputStream os = s.getOutputStream();
        os.write("Hello, TCP, 我来了".getBytes());
//        接收服务器反馈
        InputStream is = s.getInputStream();
        byte[] bys = new byte[1024];
        int len = is.read(bys);
        String data = new String(bys, 0, len);
        System.out.println("客户端: " + data);
//        释放资源, 不需要再执行os.close和is.close
        s.close();
    }
}
