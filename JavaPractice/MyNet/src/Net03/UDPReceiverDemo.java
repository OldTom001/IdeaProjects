package Net03;

import jdk.nashorn.internal.ir.WhileNode;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/*
 * * UDP收发练习2, 键盘录入数据然后发送, 录入886时停止
 *  接收端, 收发对象均为本机, 端口12345
 * 测试时, 先运行接收Demo开启接收, 然后运行发送Demo, 即可在接收Demo控制台看到接收到的数据
 * */
public class UDPReceiverDemo {
    public static void main(String[] args) throws IOException {

        DatagramSocket ds = new DatagramSocket(12345);
        while (true) {
            byte[] bys = new byte[1024];
            DatagramPacket dp = new DatagramPacket(bys, bys.length);
            ds.receive(dp);

            byte[] datas = dp.getData();
            String dataString = new String(datas, 0, datas.length);
            System.out.println("数据是: " + dataString);
        }

    }
}
