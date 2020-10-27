package InterfaceTest02;

public class PingpangCoach extends Coach implements English{
    public PingpangCoach() {
    }

    public PingpangCoach(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println("乒乓球教练吃饭");
    }

    @Override
    public void teach() {
        System.out.println("乒乓球教练教学");
    }

    @Override
    public void LearnEnglish() {
        System.out.println("乒乓球教练学英语");
    }
}
