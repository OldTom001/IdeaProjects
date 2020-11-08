package HashSetTest;

import java.util.HashSet;
/*使用HashSet存储学生对象
* 要求: 学生对象的成员变量相同, 就认为是同一个对象, 不重复存储
* 需要在学生类中重写hashcode和equals方法才能保证唯一性*/
public class HashSetDemo {
    public static void main(String[] args) {
//        创建HashSet对象
        HashSet<Student> hs = new HashSet<>();
//        创建学生对象
        Student s1 = new Student("孙悟空", 500);
        Student s2 = new Student("猪悟能", 400);
        Student s3 = new Student("沙悟净", 300);
        Student s4 = new Student("孙悟空", 500);
//        把学生对象添加到集合
        hs.add(s1);
        hs.add(s2);
        hs.add(s3);
        hs.add(s4);
//        增强for遍历
        for(Student s : hs) {
            System.out.println(s.getName() + ", " + s.getAge());
        }

    }
}
