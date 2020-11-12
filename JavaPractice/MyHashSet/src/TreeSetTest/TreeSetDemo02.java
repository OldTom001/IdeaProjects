package TreeSetTest;

import sun.reflect.generics.tree.Tree;

import java.util.Comparator;
import java.util.TreeSet;

/*s使用TreeSet存储学生对象并遍历, 测试Comparator比较器排序
 * 需要传递一个Comparator接口的实现对象, 为了方便, 这里用匿名内部类 */
public class TreeSetDemo02 {
    public static void main(String[] args) {
        /*
        * new Comparator实际上就是一个对象
        * */
        TreeSet<Student> ts = new TreeSet<>(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                int num = s1.getAge() - s2.getAge();
                int num2 = num==0 ? s1.getName().compareTo(s2.getName()) : num; //String类已经重写了compareTo
                return num2;
            }
        });
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
