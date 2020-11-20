package MethodReference04;

import java.util.function.Consumer;
/*按照格式
姓名: XXX, 年龄: XXX
打印人员信息, 用Consumer实现*/
public class ConsumerDemo02 {
    public static void main(String[] args) {
        String[] strArray = {"孙悟空,30","猪悟能,20","沙悟净,40"};
//        打印姓名
        printInfo(strArray, s -> System.out.print("姓名: " + s.split(",")[0]),
                s-> System.out.println(", 年龄: "+ Integer.parseInt(s.split(",")[1])));
//        打印年龄

    }

    private static void printInfo(String[] s, Consumer<String> con1, Consumer<String> con2) {

        for(String string : s) {
            con1.andThen(con2).accept(string);
        }
    }
}

