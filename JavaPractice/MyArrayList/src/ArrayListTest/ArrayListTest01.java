package ArrayListTest;

import java.util.ArrayList;

/*案例:存储学生对象
1. 定义学生类
2. 创建集合对象
3. 创建学生对象
4. 添加学生对象到集合中
5. 遍历集合, 采用通用遍历格式实现
当前位于步骤2*/
public class ArrayListTest01 {
    public static void main(String[] args) {
//        创建集合对象
        ArrayList<Student> array = new ArrayList<Student>();
//        创建学生对象
        Student s1 = new Student("刘亦菲", 18);
        Student s2 = new Student("风清扬", 50);
        Student s3 = new Student("川普", 3);
//        将学生对象添加到集合中
        array.add(s1);
        array.add(s2);
        array.add(s3);
//        遍历集合, 采用通用遍历格式
        for(int i = 0; i < array.size(); i++) {
            Student s = array.get(i);
            System.out.println(s.getName() + ", " +s.getAge());
        }
    }
}
