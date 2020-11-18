package Thread01;

/*
* 多线程测试
* 若使用带参构造方法, MyThread类中需要给出带参构造方法
* */
public class MyThreadDemo {
    public static void main(String[] args) {
/*//        无参构造方法, set方法修改方法名
        MyThread my1 = new MyThread();
        MyThread my2 = new MyThread();
        my1.setName("飞机");
        my2.setName("高铁");
//        启动线程, start内部会调用run
        my1.start();
        my2.start();*/

//        带参构造方法
        MyThread my1 = new MyThread("飞机");
        MyThread my2 = new MyThread("高铁");

        my1.start();
        my2.start();
//        显示当前当前进程, 也就是main, 这里会输出一个main
        System.out.println(Thread.currentThread().getName());

//

    }
}
