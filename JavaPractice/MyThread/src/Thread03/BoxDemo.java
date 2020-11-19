package Thread03;
/*
* 生产者和消费者案例, 生产者往奶箱中送奶, 消费者取奶, 双线程实现
* */
public class BoxDemo {
    public static void main(String[] args) {

        Box b = new Box();
        Producer p = new Producer(b);
        Customer c = new Customer(b);
//        第一个线程执行Producer中的操作
//        第二个线程执行Customer中的操作
        Thread t1 = new Thread(p);
        Thread t2 = new Thread(c);
//        启动线程
        t1.start();
        t2.start();
    }

}
