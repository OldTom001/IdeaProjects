package InterfaceTest;

public class Cat extends Animal implements Jumpping {
//    构造方法
    public Cat() {
    }

    public Cat(String name, int age) {
        super(name, age);
    }
//    重写父类中的抽象方法
    @Override
    public void eat() {
        System.out.println("猫吃鱼");
    }
//    重写接口中的抽象方法
    @Override
    public void jump() {
        System.out.println("猫猫可以跳高了");
    }
}
