package Net09;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * TCP练习4, 数据来自文本文件srcNet07
 * 接收后存到文本文件net07.txt
 * (与Net07共用一组txt文件)
 * 文件接收完成后, 服务器给出反馈, 客户端接收反馈
 * 服务器端代码用线程封装, 为每一个客户端提供一个线程; 客户端代码同Net08
 * 服务器可以同时接收多个客户端的文件
 * */
public class ServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10000);
        while (true) {
            Socket s = ss.accept();
//            为每一个客户端开启一个线程
            new Thread(new ServerThread(s)).start();
        }


    }
}
