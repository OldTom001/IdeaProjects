package Reflect01;

/*三种方式获取Class对象*/

public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException {
//        方法1, 使用类的Class属性
        Class<Student> c1 = Student.class;
        System.out.println(c1);

        Class<Student> c2 = Student.class;
        System.out.println(c1==c2);

        System.out.println("--------");

//        调用对象的getClass方法
        Student s1 = new Student();
        Class<? extends Student> c3 = s1.getClass();
        System.out.println(c1==c3);
        System.out.println("--------");

//        使用Class类的静态方法forName(String name)
        Class<?> c4 = Class.forName("Reflect01.Student");
        System.out.println(c4==c1);
    }
}
