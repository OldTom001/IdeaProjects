package CalendarTest;

import java.util.Calendar;

public class CalendarDemo {
    public static void main(String[] args) {
//        获取对象
        Calendar c = Calendar.getInstance();
//        月份是从0开始的
        System.out.println(c);
//        public int get​(int field)
        int year = c.get(Calendar.YEAR);
//        Callendar的月份是从0开始记的, 故+1;
        int month = c.get(Calendar.MONTH) + 1;
        int date = c.get(Calendar.DATE);
        System.out.println(year + "年" + month + "月" + date + "日");
    }
}
