package MethodReference01;
/*
* 引用类方法, 引用Integer类中的parseInt方法
* 接口Converter中有一个抽象方法
* 在测试类中使用这个抽象方法
* */
public class ConverterDemo {
    public static void main(String[] args) {
//        使用Lambda表达式
        useConverter(s -> Integer.parseInt(s));
//        使用引用类方法
        useConverter(Integer::parseInt);
    }

    private static void useConverter(Converter c) {
        int number = c.convert("666");
        System.out.println(number);
    }
}
