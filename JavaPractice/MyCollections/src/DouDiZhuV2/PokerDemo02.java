package DouDiZhuV2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

/*
* 模拟斗地主升级版, 看牌有序.
* */
public class PokerDemo02 {
    public static void main(String[] args) {
        HashMap<Integer, String> hm = new HashMap<>();
        ArrayList<Integer> array = new ArrayList<>();

        String[] colors = {"♥", "♠", "♦", "♣"};
        String[] numbers = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};

        int index = 0;

//        注意这个嵌套for, 要保证最后的牌有序, 必须是number在外层, color在内层
//        因为这里必须要保证number是从大到小排序的
//        因为TreeSet对索引排序, 我们必须要保证索引对应的值也有序
        for(String number : numbers) {
            for(String color : colors) {
                hm.put(index, color + number);
                array.add(index);
                index ++;
            }
        }
        hm.put(index, "小王");
        array.add(index);
        index ++;
        hm.put(index, "大王");
        array.add(index);
//        洗牌
        Collections.shuffle(array);
//        创建玩家
        TreeSet<Integer> swk = new TreeSet<>();
        TreeSet<Integer> zwn = new TreeSet<>();
        TreeSet<Integer> swj = new TreeSet<>();
        TreeSet<Integer> dp = new TreeSet<>();
//        发牌
        for(int i = 0; i < array.size(); i++) {
            if(i >= array.size() - 3) {
                dp.add(i);
            }
            else if(i%3 == 0) {
                swk.add(array.get(i));
            } else if(i%3 == 1) {
                zwn.add(array.get(i));
            } else if(i%3 == 2) {
                swj.add(array.get(i));
            }
        }
        look("孙悟空", swk, hm);
        look("猪悟能", zwn, hm);
        look("沙悟净", swj, hm);
        look("底牌", dp, hm);
    }

//    方法: 看牌
    public static void look(String name, TreeSet<Integer> ts, HashMap<Integer, String> hm) {
        System.out.println(name + "的牌: ");
        for(Integer key : ts) {
            String poker = hm.get(key);
            System.out.print(poker + " ");
        }
        System.out.println();
    }
}
