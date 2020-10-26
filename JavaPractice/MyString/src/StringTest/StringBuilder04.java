package StringTest;
/*构建方法, 使字符串反转*/
import java.util.Scanner;

public class StringBuilder04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串");
        String line = sc.nextLine();
//        调用反转方法
        line = reverse(line);
        System.out.println("line: " + line);

    }
    /*字符串反转方法
    * 参数: String s
    * 返回值: String*/
    public static String reverse(String s) {
//        创建对象啊ing
        StringBuilder sb = new StringBuilder(s);
//        进行反转
        sb.reverse();
//        从StringBuilder转换成String
        String ss = sb.toString();
        return ss;

/*//        方法中所有代码可以用如下一行代码实现
        return new StringBuilder(s).reverse().toString();*/
    }
}
