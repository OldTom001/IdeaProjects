package Net07;

import java.io.*;
import java.net.Socket;

/*
* TCP练习4, 数据来自文本文件srcNet07
* 接收后存到文本文件net07.txt
* */
public class ClientDemo {
    public static void main(String[] args) throws IOException {

        Socket s = new Socket("10.177.119.249", 10000);

//        封装文本文件的数据
        BufferedReader br = new BufferedReader(new FileReader("MyNet\\srcNet07"));
//        封装输出流写数据
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

        String line;
//        先读文件中的内容, 然后通过TCP协议发送
        while((line= br.readLine())!= null) {
            bw.write(line);
            bw.newLine();
            bw.flush();
        }
        br.close();
        s.close();
    }
}
