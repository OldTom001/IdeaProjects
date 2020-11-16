package ByteStream01;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/*
* 将一个目录下的源文件复制到另一个目录下.
* 注意字节流操作需要处理一个IOException
* */
public class CopyTxtDemo  {
    public static void main(String[] args) throws IOException {
//        fis指向源文件
        FileInputStream fis = new FileInputStream("E:\\Desktop\\1\\源文档.txt");
//        下面这行代码会在目录2下创建一个源文档.txt
        FileOutputStream fos = new FileOutputStream("E:\\Desktop\\2\\源文档.txt");
//        读写数据
        int by;
//        读
        while((by = fis.read()) != -1) {
//            写
            fos.write(by);
//            输出一下看看. 默认会输出ASCII码, 需要转换成char;
//            中文输出来是乱码
            System.out.print((char)by);
        }
//        释放资源
        fis.close();
        fos.close();
    }
}
