package Properties;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
/*
* 猜数字小游戏, 玩的次数存在game.txt中, 最多玩3次, 玩慢3次后提示继续玩请充值.
* game.txt中, count是键, 次数是值
* 注意txt文件中键和值必须用等号连接, 如count=0
* */
public class GuessNumberDemo {
    public static void main(String[] args) throws IOException {
        Properties prop = new Properties();
        FileReader fr = new FileReader("MyOtherStream\\game.txt");
//        将文件内容加载到Properties中
        prop.load(fr);
        fr.close();
//        通过键得到值
        String count = prop.getProperty("count");
//        将字符串解析成int
        int number = Integer.parseInt(count);
//        初始时count=0, 免费玩三次
        if(number >= 3) {
            System.out.println("游戏试玩已结束, 继续玩清充值(www.tencent.com)");
        } else {
            GuessNumber.start();
//            玩的次数更新
            number ++;
//            将更新后的次数重新加载入Properties
            prop.setProperty("count", String.valueOf(number));
            FileWriter fw = new FileWriter("MyOtherStream\\game.txt");
//            写入文件
            prop.store(fw, null);
            fw.close();
        }

    }
}
