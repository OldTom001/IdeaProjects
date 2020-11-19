package Net03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/*
 * * UDP收发练习2, 键盘录入数据然后发送, 录入886时停止
 *  发送端, 收发对象均为本机, 端口10086
 * 测试时, 先运行接收Demo开启接收, 然后运行发送Demo, 即可在接收Demo控制台看到接收到的数据
 * */
public class UDPSendDemo {
    public static void main(String[] args) throws IOException {
//        创建发送端Socket
        DatagramSocket ds = new DatagramSocket();
//        键盘录入数据
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
//            输入886发送结束
            if ("886".equals(line)) {
                break;
            }
//            大包发送数据
            byte[] bys = line.getBytes();
//            DatagramPacket​(byte[] buf, int length, InetAddress address, int port)
//            指定接收主机和端口号
            DatagramPacket dp = new DatagramPacket(bys, bys.length, InetAddress.getByName("DESKTOP-JEBDMT5"), 12345);
            ds.send(dp);
        }
        ds.close();
    }
}