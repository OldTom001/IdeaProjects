package InterfaceTest02;

public class PingpangPlayer extends Player implements English{
    public PingpangPlayer() {
    }

    public PingpangPlayer(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println("乒乓球运动员吃饭");
    }

    @Override
    public void practice() {
        System.out.println("乒乓球运动员训练");
    }

    @Override
    public void LearnEnglish() {
        System.out.println("乒乓球运动员学英语");
    }
}
