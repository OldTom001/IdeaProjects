package Net07;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * TCP练习4, 数据来自文本文件srcNet07
 * 接收后存到文本文件net07.txt
 * */
public class ServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10000);

        Socket s = ss.accept();

            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

            BufferedWriter bw = new BufferedWriter(new FileWriter("MyNet\\net07.txt"));

            String line;
//            先通过TCP读数据, 然后把读到的数据写入文件
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
                bw.flush();
            }

        bw.close();
        ss.close();

    }
}
