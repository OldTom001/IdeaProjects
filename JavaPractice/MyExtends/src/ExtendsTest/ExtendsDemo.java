package ExtendsTest;

public class ExtendsDemo {
    public static void main(String[] args) {
//        调用无参构造方法
        Student s1 = new Student();
        s1.setName("孙悟空");
        s1.setAge(50);
        System.out.println(s1.getName() + ", " + s1.getAge());
        s1.study();

        System.out.println("--------");

        Student s2 = new Student("猪八戒", 80);
        System.out.println(s2.getName() + ", " + s2.getAge());
        s2.study();

        System.out.println("--------");

        Teacher t1 = new Teacher("唐三藏", 20);
        System.out.println(t1.getName() + ", " + t1.getAge());
        t1.teach();
    }
}
