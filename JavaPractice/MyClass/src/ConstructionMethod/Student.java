package ConstructionMethod;
/*
* 构造方法
* 省略了set/get方法
* */
public class Student {
    //成员变量
    private  String name;
    private int age;

    //构造方法
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    //get/set方法(此处省略)

    //成员方法
    public void show() {
        System.out.println(name + "," + age);
    }
}
