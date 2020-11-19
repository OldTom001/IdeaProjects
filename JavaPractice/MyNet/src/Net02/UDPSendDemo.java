package Net02;

import java.io.IOException;
import java.net.*;
/*
* * UDP收发练习1, 发送端, 收发对象均为本机, 端口10086
* 测试时, 先运行接收Demo开启接收, 然后运行发送Demo, 即可在接收Demo控制台看到接收到的数据
* */
public class UDPSendDemo {
    public static void main(String[] args) throws IOException {
//        创建发送端Socket
        DatagramSocket ds = new DatagramSocket();
//        创建发送数据包
        byte[] bys = "Hello, UDP, 我来了!".getBytes();
//        DatagramPacket​(byte[] buf, int length, InetAddress address, int port)
//        制定接收端的端口号为10086
        DatagramPacket dp = new DatagramPacket(bys, bys.length, InetAddress.getByName("DESKTOP-JEBDMT5"), 10086);
//        调用发送程序
        ds.send(dp);
//        关闭发送端
        ds.close();
    }
}
