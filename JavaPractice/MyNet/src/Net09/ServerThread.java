package Net09;

import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable {
    private Socket s;
    public ServerThread(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {

        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
//            BufferedWriter bw = new BufferedWriter(new FileWriter("MyNet\\srcNet07"));
//            解决名称冲突问题, 没开启一个客户端就新建一个文件.
            int count = 0;
            File file = new File("MyNet\\net07[" + count + "]");
            while (file.exists()) {
                count++;
                file = new File("MyNet\\net07[" + count + "]");
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            String line;
//            读数据并写入文件
            while ((line = br.readLine())!=null) {
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
//            给出反馈
            BufferedWriter bwServer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            bwServer.write("文件上传成功");
            bwServer.newLine();
            bwServer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
