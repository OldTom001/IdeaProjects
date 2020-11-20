package MethodReference02;
/*
* 引用构造器
* */
public class StudentDemo {
    public static void main(String[] args) {

//        Lambda表达式
       /* useStudentBuilder((String name, int age) ->{
            Student s = new Student(name, age);
            return s;
        });*/
//        Lambda表达式省略形式
        useStudentBuilder((name, age)->new Student(name, age));
//        引用构造器
        useStudentBuilder(Student::new);
    }

    private static void useStudentBuilder(StudentBuilder sb) {
        Student s = sb.build("林青霞", 30);
        System.out.println(s.getName() + ", " + s.getAge());
    }
}
