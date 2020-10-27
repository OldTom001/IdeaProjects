package InterfaceTest02;

public class InterfaceDemo {
    public static void main(String[] args) {
        BasketCoach bc = new BasketCoach("王治郅", 40);
        System.out.println(bc.getName() + ", " + bc.getAge());
        bc.eat();
        bc.teach();
        System.out.println("----------------");

        BasketPlayer bp = new BasketPlayer("易建联", 30);
        System.out.println(bp.getName() + ", " + bp.getAge());
        bp.eat();
        bp.practice();
        System.out.println("----------------");

        PingpangCoach pc = new PingpangCoach("刘国梁", 50);
        System.out.println(pc.getName() + ", " + pc.getAge());
        pc.eat();
        pc.teach();
        pc.LearnEnglish();
        System.out.println("----------------");

        PingpangPlayer pp = new PingpangPlayer("丁宁", 25);
        System.out.println(pp.getName() + ", " + pp.getAge());
        pp.eat();
        pp.practice();
        pp.LearnEnglish();
        System.out.println("----------------");
    }


}
