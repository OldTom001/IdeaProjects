package InterfaceTest;

public class AnimalDemo {
    public static void main(String[] args) {
//        先用接口实现
        System.out.println("接口实现:");
        Jumpping j = new Cat();
        ((Cat) j).setName("胖橘");
        ((Cat) j).setAge(6);
        System.out.println(((Cat) j).getName() + ", " + ((Cat) j).getAge());
        ((Cat) j).eat();
        j.jump();
        System.out.println("---------------");
//        再用抽象类实现
        System.out.println("抽象类实现: ");
        Animal a = new Cat();
        a.setName("汤姆");
        a.setAge(8);
        System.out.println(a.getName() + ", " + a.getAge());
        a.eat();
        ((Cat) a).jump();

        Animal a1 = new Dog("旺财", 5);
        System.out.println(a1.getName() + ", " + a1.getAge());
        a1.eat();
        ((Dog) a1).jump();
        System.out.println("----------------");
//        下面是最常用的方法, 即用具体类实现
        System.out.println("具体类实现: ");
        Dog d = new Dog("藏獒", 7);
        System.out.println(d.getName() + ", " + d.getAge());
        d.eat();
        d.jump();
    }
}
