package Thread03;

public class Box {

    private int milk;
    private boolean state;

//    使用wait方法必须加synchronized关键字, 因为wait方法必须在同步代码中使用
    public synchronized void put(int milk) {
//        若有奶, 则等待消费者取奶
        if (state) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.milk = milk;
        System.out.println("送奶工将第" + this.milk + "瓶奶放入奶箱");
        state = true;
//        唤醒线程, 目的是唤醒get线程, notify和notifyAll都可以
//        notify();
        notifyAll();
    }

    public synchronized void get() {
//        若没有奶, 则等待生产者放奶
        if(!state) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("用户拿到了第" + this.milk + "瓶奶");
        state = false;
//        唤醒线程, 目的是唤醒put线程
//        notifyAll();
        notifyAll();
    }
}
