package MethodReference05;

import java.util.ArrayList;
import java.util.function.Predicate;

/*打印输出名字大于两个字, 且年龄大于33岁的人的姓名,
* 用函数式接口Predicate实现*/

public class PredicateDemo {
    public static void main(String[] args) {

        String[] strArray = {"林青霞,30", "柳岩,34", "张曼玉,35", "貂蝉,31", "王祖贤,33"};
//        将字符串用逗号分割, 然后判断
        ArrayList<String> array = myFilter(strArray, s -> s.split(",")[0].length() > 2,
                s -> Integer.parseInt(s.split(",")[1]) > 33);
//        输出最终结果(已保存在集合中)
        for(String str : array) {
            System.out.println(str);
        }
    }

    public static ArrayList<String> myFilter(String[] str, Predicate<String> pre1, Predicate<String> pre2) {

        ArrayList<String> array = new ArrayList<>();
//        先判断姓名长度是否大于2, 再判断年龄是否大于33, 对两个判断结果进行与运算.
//        若符合条件, 则存入集合
        for (String s : str) {
            if (pre1.and(pre2).test(s)) {
                array.add(s);
            }
        }
        return array;
    }
}
