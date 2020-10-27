package PolymTest;

public class AnimalDemo {
    public static void main(String[] args) {
        AnimalOpetator ao = new AnimalOpetator();
        Cat c = new Cat();
        ao.useAnimal(c);

        Dog d = new Dog();
        ao.useAnimal(d);

        Animal a = new Animal();
        ao.useAnimal(a);
    }
}
