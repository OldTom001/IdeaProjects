package StringTest;

import java.util.Arrays;

public class StringSort {
    public static void main(String[] args) {
//        定义一个字符串
        String s = "91 27 46 38 50";
//       去掉分隔符号(此处是空格), 然后放到一个String类型的数组中
        String[] strArray = s.split(" ");
//        测试一下对不对
        /*for(int i = 0; i < strArray.length; i++) {
            System.out.println(strArray[i]);
        }*/
//        定义一个int数组并指定长度, 将String类型的数组转变成int类型的数组
        int[] arr = new int[strArray.length];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(strArray[i]);
        }
//        排序
        Arrays.sort(arr);
//        拼接得到字符串
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length; i++) {
            if(i == arr.length - 1) {
                sb.append(arr[i]);
            } else {
                sb.append(arr[i]).append(" ");
            }
        }
        String result = sb.toString();
        System.out.println("result: " + result);
    }

}
