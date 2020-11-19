package Net02;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/*
* UDP收发练习1, 接收端, 收发对象均为本机, 端口10086
* 测试时, 先运行接收Demo开启接收, 然后运行发送Demo, 即可在接收Demo控制台看到接收到的数据
* */
public class UDPReceiveDemo {
    public static void main(String[] args) throws IOException {
//        创建接收端Socket, 接收端口10086
        DatagramSocket ds = new DatagramSocket(10086);
//        创建一个数据包, 用于接收数据
        byte[] bys = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bys, bys.length);
//        开启接收
        ds.receive(dp);
//        解析数据并打印
        byte[] datas = dp.getData();
        int len = dp.getLength();
        String dataString = new String(datas, 0, len);
        System.out.println("数据是: " + dataString);
        ds.close();
    }
}
