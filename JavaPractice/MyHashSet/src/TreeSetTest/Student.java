package TreeSetTest;

import java.util.Comparator;

public class Student implements Comparable<Student> {
    //    成员变量
    private String name;
    private int age;

    //    构造方法
    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //    成员方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
   /* 重写Comparable接口中的抽象方法, 这就是排序规则
    返回0, 则TreeSet认为两个元素相等,
    返回正数, 则升序排列
    返回复数, 则降序排列*/
    @Override
    public int compareTo(Student s) {
        int num = this.age - s.age;
        int num2 = num == 0 ? this.name.compareTo(s.name) : num;
        return num2;
    }
}

