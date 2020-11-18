package Properties;

import java.util.Random;
import java.util.Scanner;

public class GuessNumber {
//    构造方法
    private GuessNumber() {
    }
//    成员方法
    public static void start() {

        Random r = new Random();
        int number = r.nextInt(100) + 1;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入你要猜的数字: ");
            int guessnumber = sc.nextInt();
//            比较系统生成的数字和玩家猜的数字
            if(guessnumber > number) {
                System.out.println("你猜的数字" + guessnumber + "大了");
            } else if (guessnumber < number) {
                System.out.println("你猜的数字" + guessnumber + "小了");
            } else {
                System.out.println("恭喜你猜中了!");
                break;
            }
        }
    }

}
