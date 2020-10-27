package AbstractTest;

public class AnimalDemo {
    public static void main(String[] args) {
        Animal a = new Cat();
        a.setName("汤姆");
        a.setAge(5);
        System.out.println(a.getName() + "," + a.getAge());
        a.eat();

        a = new Dog("金毛", 8);
        System.out.println(a.getName() + ", " + a.getAge());
        a.eat();
    }
}
