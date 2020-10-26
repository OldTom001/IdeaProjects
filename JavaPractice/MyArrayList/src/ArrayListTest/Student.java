package ArrayListTest;
/*案例:存储学生对象
1. 定义学生类
2. 创建集合对象
3. 添加学生对象到集合中
4. 遍历集合, 采用通用遍历格式实现
当前位于步骤1*/
public class Student {
//    成员方法
    private String name;
    private int age;
//    构造方法
    public Student() {}
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
//    get/set方法
    public void setName(String name) {
        this.name = name;
    }
    public  void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
}
