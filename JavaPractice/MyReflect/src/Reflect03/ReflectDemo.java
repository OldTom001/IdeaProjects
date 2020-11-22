package Reflect03;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/*练习: 反射获取成员方法并使用
* Student类在Reflect01中*/

public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
//        获取Class对象
        Class<?> c = Class.forName("Reflect01.Student");
//        获取无参构造方法
        Constructor<?> con = c.getConstructor();
//        通过无参构造方法创建对象
        Object obj = con.newInstance();

//        给obj对象赋值: 姓名
//        获取成员变量
        Field nameField = c.getDeclaredField("name");
//        暴力反射, 取消访问检查, 强行访问私有变量
        nameField.setAccessible(true);
//        给obj对象的nameField对象赋值
        nameField.set(obj, "林青霞");
        System.out.println(obj);
//        赋值年龄
        Field ageField = c.getDeclaredField("age");
        ageField.setAccessible(true);
        ageField.set(obj, 30);
        System.out.println(obj);
//        赋值地址
        Field addressField = c.getDeclaredField("address");
//        公共变量可以不写下面这一句
        addressField.setAccessible(true);
        addressField.set(obj, "西安");
        System.out.println(obj);

    }
}
