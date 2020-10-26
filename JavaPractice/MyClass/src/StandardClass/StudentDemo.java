package StandardClass;

public class StudentDemo {
    public static void main(String[] args) {
        //创建对象
        Student s = new Student("林青霞", 30);
        s.show();

        //使用set方法给成员变量赋值
        s.setName("川普");
        s.setAge(600);
        s.show();
        //使用get方法获取成员变量的值
        System.out.println(s.getName() + "---" + s.getAge());
    }
}
/*
* 修改1
*
* 修改2
*
* 修改3
* */