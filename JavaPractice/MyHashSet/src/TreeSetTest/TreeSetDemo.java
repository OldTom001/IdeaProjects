package TreeSetTest;

import java.util.TreeSet;
/*s使用TreeSet存储学生对象并遍历, 测试自然排序
* 因为Student类中没有实现Comparable接口, 因此需要手动实现, 否则会报错.
* 手动实现接口不要忘记重写抽象方法*/
public class TreeSetDemo {
    public static void main(String[] args) {
        TreeSet<Student> ts = new TreeSet<>();
        Student s1 = new Student("sunwukong", 50);
        Student s2 = new Student("zhuwuneng", 10);
        Student s3 = new Student("shawujing", 30);
        Student s4 = new Student("tangsanzang", 20);

        ts.add(s1);
        ts.add(s2);
        ts.add(s3);
        ts.add(s4);

        for(Student s : ts) {
            System.out.println(s.getName() + ", " + s.getAge());
        }
    }
}
