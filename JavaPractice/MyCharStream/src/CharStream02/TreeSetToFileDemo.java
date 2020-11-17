package CharStream02;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;
/*
* 键盘录入3个学生信息(姓名, 语文成绩, 数学成绩, 英语成绩), 按照总成绩从高到低写入文本文件
* 格式: 姓名, 语文成绩, 数学成绩, 英语成绩, 总成绩.
* */
public class TreeSetToFileDemo {
    public static void main(String[] args) throws IOException {
//        TreeSet集合保证降序排列
        TreeSet<Student> ts = new TreeSet<>(new Comparator<Student>(){
            @Override
            public int compare(Student s1, Student s2) {
//                降序排列
                int num1 = s2.getSum() - s1.getSum();
                int num2 = num1 == 0 ? (s2.getChinese()-s1.getChinese()) : num1;
                int num3 = num2 == 0 ? (s2.getMath() - s1.getMath()) : num2;
                int num4 = num3 == 0 ? (s2.getEnglish() - s1.getEnglish()) : num3;
                return num4;
            }
        });
//        键盘输入
        for (int i = 0; i < 3; i++) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请录入第" + (i+1) + "个学生信息.");
            System.out.println("姓名: ");
            String name = sc.nextLine();
            System.out.println("语文成绩:");
            int chinese = sc.nextInt();
            System.out.println("数学成绩: ");
            int math = sc.nextInt();
            System.out.println("英语成绩");
            int english = sc.nextInt();
            Student s1 = new Student();
            s1.setName(name);
            s1.setChinese(chinese);
            s1.setMath(math);
            s1.setEnglish(english);
//            将学生信息添加到TreeSet
            ts.add(s1);
        }
//        创建字符缓冲输出流对象
        BufferedWriter bw = new BufferedWriter(new FileWriter("MyCharStream\\ts.txt"));
        for(Student student : ts){
            StringBuilder sb = new StringBuilder();
//            拼接
            sb.append(student.getName()).append(", ").append(student.getChinese()).append(", ").append(student.getMath()).append(", ").append(student.getEnglish()).append(", ").append(student.getSum());
//            写数据
            bw.write(sb.toString());
            bw.newLine();
            bw.flush();
        }
//        释放资源
        bw.close();
    }
}
