package StringTest;

import java.util.Scanner;

public class BigOrSmallOrNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入一个字符串");
        String line = sc.nextLine();

        int bigCount = 0;
        int smallCount = 0;
        int numCount = 0;

        for(int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if(line.charAt(i) >= 'A' && line.charAt(i) <= 'Z') {
                bigCount ++;
            } else if(line.charAt(i) >= 'a' && line.charAt(i) <= 'z') {
                smallCount ++;
            } else if(line.charAt(i) >= '0' && line.charAt(i) <= '9'){
                numCount ++;
            }
        }
        System.out.println("大写字母个数为: " + bigCount);
        System.out.println("小写字母个数为: " + smallCount);
        System.out.println("数字个数为: " + numCount);
    }
}
