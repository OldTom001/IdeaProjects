package DouDiZhuV1;

import java.util.ArrayList;
import java.util.Collections;

/*
* 模拟斗地主中的洗牌, 发牌和看牌过程
* */
public class PokerDemo01 {
    public static void main(String[] args) {
        ArrayList<String> array = new ArrayList<>();
//      装牌
//          定义花色数组
        String[] colors = {"♦", "♣", "♥", "♠"};
//        定义数字数组
        String[] numbers = {"2","3","4","5","6","7","8","9","10", "J","Q","K","A"};
        for(String color : colors) {
            for(String number : numbers) {
                array.add(color + number);
            }
        }
        array.add("大王");
        array.add("小王");
//        随机排序, 模拟洗牌
        Collections.shuffle(array);

        ArrayList<String> swk = new ArrayList<>();
        ArrayList<String> zwn = new ArrayList<>();
        ArrayList<String> swj = new ArrayList<>();
        ArrayList<String> dp = new ArrayList<>();

        for(int i = 0; i < array.size(); i++){
            String poker = array.get(i);
            if(i >= array.size()  - 3) {
                dp.add(poker);
            } else if(i%3 == 0) {
                swk.add(poker);
            } else if(i%3 == 1) {
                zwn.add(poker);
            } else if(i%3 == 2) {
                swj.add(poker);
            }
        }

        look("孙悟空", swk);
        look("猪悟能", zwn);
        look("沙悟净", swj);
        look("底牌", dp);


    }
//    方法: 看牌
    public static void look(String name, ArrayList<String> al) {
        System.out.println(name + "的牌是: ");
        for(String s : al) {
            System.out.print(s + "");
        }
        System.out.println();
    }
}
