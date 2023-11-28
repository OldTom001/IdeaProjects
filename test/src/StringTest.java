import java.util.Scanner;

public class StringTest {
    public static void main(String[] args) {
//        String s1 = new String("1") ;
//        s1.intern();
//        String s2 = "1";
//        System.out.println(s1== s2); //false

//        String s3 = new String("1") + new String("1");
//        s3.intern();
//        String s4 = "11";
//        System.out.println(s3 == s4); //true
//
//        String s5 = "1" + "1";
//        s5.intern();
//        String s6 = "11";
//        System.out.println(s5 == s6); //true

        String s1 = new StringBuilder("0").append("1").toString();
        System.out.println(s1.intern() == s1);  //true
        String s2 = new StringBuilder("0").toString();
        System.out.println(s2.intern() == s2);  //false

    }
}

