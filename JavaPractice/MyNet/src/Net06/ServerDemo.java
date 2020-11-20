package Net06;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TransferQueue;

/*
 *  TCP练习3: TPC通信实验, 服务器端, 将客户端发送的数据存入文件
 * */
public class ServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10000);

        Socket s = ss.accept();

            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

            BufferedWriter bw = new BufferedWriter(new FileWriter("MyNet\\s.txt"));

            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
                bw.flush();
            }

        bw.close();
        ss.close();

    }
}
