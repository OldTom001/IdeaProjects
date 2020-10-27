package PolymTest;

public class AnimalOpetator {
    public void useAnimal(Animal a) {
//        这里体现多态
//        Animal a = new Cat();
//        然后执行a.eat(), 编译看Animal, 执行看Cat
        a.eat();
        /*eat是父类中定义过的方法, 因此可以用多态使用;
        * 若子类中有特有方法, 则此处不能调用(因为编译看左边, 执行看右边)*/
    }
}
