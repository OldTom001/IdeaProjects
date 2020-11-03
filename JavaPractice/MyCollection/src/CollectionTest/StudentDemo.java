package CollectionTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class StudentDemo {
    public static void main(String[] args) {
//        创建集合对象
        Collection<Student> c = new ArrayList<>();
//        创建学生对象
        Student s1 = new Student("孙悟空", 30);
        Student s2 = new Student("猪悟能", 40);
        Student s3 = new Student("沙悟净", 50);
//        把学生添加到集合
        c.add(s1);
        c.add(s2);
        c.add(s3);
//        遍历集合(迭代器方式)
        Iterator<Student> it = c.iterator();
//        判断下一个元素是否存在, 若存在则输出
        while (it.hasNext())
        {
            Student ss = it.next();
            System.out.println(ss.getName() + ", " + ss.getAge());
        }
    }
}
