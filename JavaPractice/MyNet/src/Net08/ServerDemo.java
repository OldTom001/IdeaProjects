package Net08;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * TCP练习5, 数据来自文本文件srcNet07
 * 接收后存到文本文件net07.txt
 * (与Net07共用一组txt文件)
 * 文件接收完成后, 服务器给出反馈, 客户端接收反馈
 * */
public class ServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10000);

        Socket s = ss.accept();

        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        BufferedWriter bw = new BufferedWriter(new FileWriter("MyNet\\net07.txt"));

        String line;
//            先通过TCP读数据, 然后把读到的数据写入文件
        while ((line = br.readLine()) != null) { //等待读取数据
            bw.write(line);
            bw.newLine();
            bw.flush();
        }

//        服务器给出反馈
        BufferedWriter bwServer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        bwServer.write("文件上传成功. ");
        bwServer.newLine();
        bwServer.flush();

//        释放资源
        bw.close();
        ss.close();

    }
}
