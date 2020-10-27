package InterfaceTest02;

public class BasketPlayer extends Player {
    public BasketPlayer() {
    }

    public BasketPlayer(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println("篮球运动员吃饭");
    }

    @Override
    public void practice() {
        System.out.println("篮球运动员训练");
    }

}
