package Stream01;

/*Stream流练习
* 男演员只要名字为三个字的前三人
* 女演员只要姓林的, 并且不要第一个
* 用上述筛选结果创建Actor对象, 并遍历输出*/
import java.util.ArrayList;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {

        ArrayList<String> manList = new ArrayList<>();
        manList.add("周润发");
        manList.add("成龙");
        manList.add("刘德华");
        manList.add("吴京");
        manList.add("周星驰");
        manList.add("李连杰");

        ArrayList<String> womanList = new ArrayList<>();
        womanList.add("林心如");
        womanList.add("张曼玉");
        womanList.add("林青霞");
        womanList.add("柳岩");
        womanList.add("林志玲");
        womanList.add("王祖贤");
//        男演员只要名字为三个字的前三人
        Stream<String> manStream = manList.stream().filter(s -> s.length() == 3).limit(3);
//        女演员只要姓林的, 并且不要第一个
        Stream<String> womanStream = womanList.stream().filter(s -> s.startsWith("林")).skip(1);
//        合并上面两个流
        Stream<String> stream = Stream.concat(manStream, womanStream);
//        把上一步操作后的元素作为构造方法创建Actor对象, 并遍历数据
        stream.map(Actor::new).forEach(p-> System.out.println(p.getName()));
        System.out.println("--------");

//        把上面的代码合并成一行
        Stream.concat(manList.stream().filter(s -> s.length()==3).limit(3),
                womanList.stream().filter(s -> s.startsWith("林")).skip(1))
                .map(Actor::new).forEach(p-> System.out.println(p.getName()));
    }
}
