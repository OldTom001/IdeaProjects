package Net06;

import java.io.*;
import java.net.Socket;

/*
 * TCP练习3: TPC通信实验, 客户端, 数据来自于键盘输入, 输入886时, 发送结束
 *
 * */
public class ClientDemo {
    public static void main(String[] args) throws IOException {
//        创建客户端Socket对象
        Socket s = new Socket("10.177.119.249", 10000);

//        键盘输入
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        封装输出流对象
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

            String line;
            while ((line = br.readLine()) != null) {
                if ("886".equals(line)) {
                    break;
                }
                bw.write(line);
                bw.newLine();
//                必须刷新流, 不然写不进去
                bw.flush();
            }

//        释放资源
           s.close();
    }
}
