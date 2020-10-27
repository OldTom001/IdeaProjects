package ExtendsTest;

public class Student extends Person {
//    定义无参构造方法
    public Student() {
    }
//    定义带参构造方法
    public Student(String name, int age) {
//        下面是错误的, 因为子类不能访问父类的私有成员变量
        /*this.name = name;
        this.age = age;*/
//        应该通过调用带参构造方法完成赋值
        super(name, age);
    }
    public void study() {
        System.out.println("用心学好每一门课");
    }
}
