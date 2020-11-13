package SetNested01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
/*
* 集合嵌套, 用ArrayList集合存储HashMap元素并遍历
* */
public class SetNestedDemo01 {
    public static void main(String[] args) {
        ArrayList<HashMap<String, String>> al = new ArrayList<>();
        HashMap<String, String> hm1 = new HashMap<>();
        hm1.put("杨过", "小龙女"); //为哈希map添加元素
        hm1.put("郭靖", "黄蓉");
        al.add(hm1); //将哈希map添加到ArrayList

        HashMap<String, String> hm2 = new HashMap<>();
        hm2.put("乔峰", "阿朱");
        hm2.put("张三丰", "郭襄");
        al.add(hm2);

        HashMap<String, String> hm3 = new HashMap<>();
        hm3.put("张无忌", "赵敏");
        hm3.put("段誉", "王语嫣");
        al.add(hm3);
//        遍历, 因为是嵌套集合, 所以需要嵌套遍历
//        外层遍历ArrayList, 内层遍历HashMap(这里用通过键找值的方法)
        for(HashMap<String, String> hm : al) {
            Set<String> keySet = hm.keySet();
            for(String key : keySet) {
                String value = hm.get(key);
                System.out.println(key + ", " + value);
            }
        }



    }
}
