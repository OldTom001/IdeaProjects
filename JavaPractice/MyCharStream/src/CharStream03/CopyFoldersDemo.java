package CharStream03;

import java.io.*;

public class CopyFoldersDemo {
    public static void main(String[] args) throws IOException{
//        源目录
        File srcFile = new File("E:\\Desktop\\西电");
//        目的地目录
        File destFile = new File("E:\\");
        copyFolder(srcFile, destFile);
    }

    public static void copyFile(File srcFile, File destFile) throws IOException {
//        创建字节流
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));
        byte[] bys = new byte[1024];
        int len;
        while ((len = bis.read(bys))!=-1) {
            bos.write(bys, 0, len);
        }
        bis.close();
        bos.close();
    }
//    递归复制整个文件夹
    public static void copyFolder(File srcFile, File destFile) throws IOException{
        if(srcFile.isDirectory()) {
            String srcFileName = srcFile.getName();
            File newFolder = new File(destFile, srcFileName);
//            若是个目录, 则先在目的地建一个同名目录
            if(!newFolder.exists()) {
                newFolder.mkdir();
            }
//            遍历源目录, 找到所有下级目录或文件
            File[] fileArray = srcFile.listFiles();
            for(File file: fileArray) {
//                递归
                copyFolder(file, newFolder);
            }
        } else {
//            递归出口, 遍历目录最终找到文件, 则复制文件
            File newFile = new File(destFile, srcFile.getName());
            copyFile(srcFile, newFile);
        }

    }
}
