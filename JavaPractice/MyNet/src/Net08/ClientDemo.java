package Net08;

import java.io.*;
import java.net.Socket;

/*
* TCP练习5, 数据来自文本文件srcNet07
* 接收后存到文本文件net07.txt
* (与Net07共用一组txt文件)
* 文件接收完成后, 服务器给出反馈, 客户端接收反馈
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
//        数据发送结束标记, 当服务器端收到这个标记时, 认为发送结束, 给出反馈
//        若没有发送结束标记, 服务器将一直在读数据处等待, 无法给出反馈
//        也可手动定义结束标志, 并在服务器中判断, 但若操作的文件中也含有该标志, 会出错
        s.shutdownOutput();

//        接收反馈
        BufferedReader brClient = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String feedBack = brClient.readLine();//等待读取数据
        System.out.println("服务器的反馈: " + feedBack);

        br.close();
        s.close();
    }
}
