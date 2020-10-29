package DateTransform;

import java.text.ParseException;
import java.util.Date;

public class DateDemo {
    public static void main(String[] args) throws ParseException {
//        日期格式化
        Date d = new Date();
        System.out.println(d);
        String s1 = DateUtils.dateToString(d, "yyyy年MM月dd日 HH;mm:ss");
        System.out.println(s1);
        System.out.println("----------------");
//        日期解析
        String s2 = "2020-10-10 11;11;11";
        Date d1 = DateUtils.stringToDate(s2, "yyyy-MM-dd HH;mm;ss");
        System.out.println(d1);
    }
}