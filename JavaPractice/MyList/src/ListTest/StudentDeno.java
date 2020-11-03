package ListTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StudentDeno {
    public static void main(String[] args) {
//        创建List集合对象
        List<Student> list = new ArrayList<>();
//        创建学生对象
        Student s1 = new Student("孙悟空", 40);
        Student s2 = new Student("猪悟能", 40);
        Student s3 = new Student("沙悟净", 40);
//        把学生添加到到集合
        list.add(s1);
        list.add(s2);
        list.add(s3);
//        迭代器
        Iterator<Student> it = list.iterator();
        while(it.hasNext()) {
            Student s = it.next();
            System.out.println(s.getName() + ", " + s.getAge());
        }
        System.out.println("--------");
//        普通for循环
        for(int i = 0; i < list.size(); i++) {
            Student s = list.get(i);
            System.out.println(s.getName() + ", " + s.getAge());
        }
        System.out.println("--------");
//        增强for循环
        for(Student s : list) {
            System.out.println(s.getName() + ", " + s.getAge());
        }
    }
}
