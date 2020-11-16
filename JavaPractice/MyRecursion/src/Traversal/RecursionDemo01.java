package Traversal;

import java.io.File;

/*
 * 遍历一个目录下的所有文件, 输出绝对路径
 * */
public class RecursionDemo01 {
    public static void main(String[] args) {
        File srcFile = new File("E:\\IdeaProjects");
        getAllFilePath(srcFile);
    }

    public static void getAllFilePath(File srcFile) {
//        获取给定目录下的File数组
        File[] fileArray = srcFile.listFiles();
//        遍历File数组, 得到每一个File对象
        if (fileArray != null) {
            for (File file : fileArray) {
//                是文件则直接输出
                if (file.isFile()) {
                    System.out.println(file);
//                    是目录则递归遍历
                } else if (file.isDirectory()) {
                    getAllFilePath(file);
                }
            }

        }
    }
}
