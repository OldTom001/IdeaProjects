package Thread02;
/*
* 模拟卖票, 三个窗口卖100张票, 用三个线程实现
* 多线程第2中方法实现, 即创建一个类实现Runnable接口
* */
public class SellTicketDemo {
    public static void main(String[] args) {
        SellTicket st = new SellTicket();
//        带参构造方法
        Thread t1 = new Thread(st, "窗口1");
        Thread t2 = new Thread(st, "窗口2");
        Thread t3 = new Thread(st, "窗口3");
//        长江学者
        t1.start();
        t2.start();
        t3.start();
    }
}
