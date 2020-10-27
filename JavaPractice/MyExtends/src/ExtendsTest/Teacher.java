package ExtendsTest;

public class Teacher extends Person {
//    无参构造方法

    public Teacher() {
    }

    public Teacher(String name, int age) {
        super(name, age);
    }
    public void teach() {
        System.out.println("用爱成就每一名学员");
    }
}
