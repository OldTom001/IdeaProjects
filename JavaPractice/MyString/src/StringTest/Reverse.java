package StringTest;

import java.util.Scanner;

public class Reverse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串:");
        String line = sc.nextLine();
        String s = strReverse(line);
        System.out.println("s: " + s);
    }
    public static String strReverse(String s) {
        String ss = "";
        /*倒序遍历, 然后赋值给一个新的字符串*/
        for(int i = s.length()-1; i >= 0; i --) {
            ss += s.charAt(i);
        }
        return  ss;
    }

}
