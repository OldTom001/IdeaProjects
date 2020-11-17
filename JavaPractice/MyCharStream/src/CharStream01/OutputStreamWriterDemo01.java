package CharStream01;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
/*
* 字符流写数据
* */
public class OutputStreamWriterDemo01 {
    public static void main(String[] args) throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("MyCharStream\\ows.txt"));
//        注意, 用字符流写数据时存在缓冲区, 需要调用flush方法刷新后才能真正写入到文件中;
//        释放资源(调用close)也可自动刷新
        osw.write("中国");
//        刷新流
      //  osw.flush();
        osw.close();
    }
}
