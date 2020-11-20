package Net05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
/*
 * TCP练习2: TPC通信实验, 数据来自于键盘输入, 输入886时, 发送结束
 * */
public class ServerDemo {
    public static void main(String[] args) throws IOException {
//        创建服务端Socket对象
        ServerSocket ss = new ServerSocket(10000);
//        监听客户端的连接, 返回一个对应的Socket对象
        Socket s = ss.accept();

//        获取输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String line;
        while ((line = br.readLine())!= null) {
            System.out.println(line);
        }
        ss.close();
    }
}
