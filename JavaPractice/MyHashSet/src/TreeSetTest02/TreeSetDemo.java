package TreeSetTest02;

import java.util.TreeSet;

/*s使用TreeSet存储学生对象并遍历, 测试Comparator比较器排序
 * 需要传递一个Comparator接口的实现对象, 为了方便, 这里用匿名内部类 */
public class TreeSetDemo {
    public static void main(String[] args) {
//        创建Comparator接口的实现类对象
        comImp ci = new comImp();
//        带参构造方法
        TreeSet<Student> ts = new TreeSet<>(ci);

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
