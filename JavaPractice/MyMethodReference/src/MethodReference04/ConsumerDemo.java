package MethodReference04;

import java.util.function.Consumer;

/*使用函数式接口Consumer操作字符串*/

public class ConsumerDemo {
    public static void main(String[] args) {

//        直接输出, 不进行其他操作, Lambda表达式
        OperatorString("孙悟空", s -> System.out.println(s));
//        方法引用
        OperatorString("孙悟空", System.out::println);

        System.out.println("--------");
//        用两种方式操作同一个字符串
        OperatorString("猪悟能", s -> System.out.println(s), s -> {
            StringBuilder sb = new StringBuilder(s);

            String s1 = sb.reverse().toString();
            System.out.println(s1);
        });
//        优化一下
        OperatorString("猪悟能",s -> System.out.println(s), s -> System.out.println(new StringBuilder(s).reverse().toString()));

    }
//        用两种方法操作同一个字符串
    private static void OperatorString(String name, Consumer<String> con1, Consumer<String> con2) {

//        con1.accept(name);
//        con2.accept(name);
//        下面这句话跟上面两句话是一样的, 都是操作两个字符串, 操作方法由Lambda或函数引用给出
        con1.andThen(con2).accept(name);

    }

    //    对一个字符串进行操作, 操作方法由Lambda表达式或方法引用给出
    private static void OperatorString(String name, Consumer<String> con) {
        con.accept(name);
    }
}
