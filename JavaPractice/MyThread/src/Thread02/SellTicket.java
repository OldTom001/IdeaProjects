package Thread02;

public class SellTicket implements Runnable {
    /*
    * 使用同步代码块解决多线程的数据安全问题
    * */
    //    总共100张票
    private int tickets = 300;
    private Object obj = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized (obj) {
                if (tickets > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//              输出哪个线程卖了第几张票
                    System.out.println(Thread.currentThread().getName() + "正在出售第" + (300 - tickets + 1) + "张票.");
                    tickets--;
                }
            }
        }
    }
}
