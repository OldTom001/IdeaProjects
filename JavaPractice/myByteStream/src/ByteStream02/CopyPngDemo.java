package ByteStream02;

import java.io.*;

/*
* 四种方法复制图片
* 1. 基本字节流一次读写一个字节 15933毫秒
* 2. 基本字节流一次读取一个字节数组 25ms
* 3. 字节缓冲流一次读写一个字节 104ms
* 4. 字节缓冲流一次读写一个字节数组 10ms
* 测试前需要先建立 E:\\Desktop\\1\\图片.png
* */
public class CopyPngDemo {
    public static void main(String[] args) throws IOException{
        long startTime = System.currentTimeMillis();
//        复制图片
        method04();
        long endTime = System.currentTimeMillis();
        System.out.println("共耗时: " + (endTime - startTime) + "毫秒");
    }


//        基本字节流一次读写一个字节
    public static void method01() throws IOException {
        FileInputStream fis = new FileInputStream("E:\\Desktop\\1\\图片.png");
        FileOutputStream fos = new FileOutputStream("E:\\Desktop\\2\\图片.png");

        int by;
        while((by = fis.read()) != -1) {
            fos.write(by);
        }
        fis.close();
        fos.close();
    }

    //        基本字节流一次读写一个字节数组
    public static void method02() throws IOException {
        FileInputStream fis = new FileInputStream("E:\\Desktop\\1\\图片.png");
        FileOutputStream fos = new FileOutputStream("E:\\Desktop\\2\\图片.png");

        byte[] bys = new byte[1024];
        int len;
        while((len = fis.read(bys)) != -1) {
            fos.write(bys, 0, len);
        }
        fis.close();
        fos.close();
    }
//    字节缓冲流一次读写一个字节
    public static void method03() throws IOException{

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("E:\\Desktop\\1\\图片.png"));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("E:\\Desktop\\2\\图片.png"));

        int by;
        while((by = bis.read()) != -1) {
            bos.write(by);
        }
        bis.close();
        bos.close();
    }

//    字节缓冲流一次读写一个字节数组
public static void method04() throws IOException{

    BufferedInputStream bis = new BufferedInputStream(new FileInputStream("E:\\Desktop\\1\\图片.png"));
    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("E:\\Desktop\\2\\图片.png"));

    byte bys[] = new byte[1024];
    int len;
    while((len = bis.read(bys)) != -1) {
        bos.write(bys, 0, len);
    }
    bis.close();
    bos.close();
}
}
