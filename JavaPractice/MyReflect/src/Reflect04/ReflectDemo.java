package Reflect04;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/*通过配置文件运行类中的方法
* 若想改变目前的方法, 只需修改class.txt文件中的类名和方法名即可*/

public class ReflectDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        将配置文件class.txt加载到Properties类中
        Properties prop = new Properties();
        FileReader fr = new FileReader("MyReflect\\class.txt");
        prop.load(fr);
        fr.close();

//        通过键找到类名和方法名
        String methodName = prop.getProperty("methodName");
        String className = prop.getProperty("className");

//        通过放射创建对象
        Class<?> c = Class.forName(className);
        Constructor<?> con = c.getConstructor();
        Object obj = con.newInstance();
//        调用方法
        Method m = c.getMethod(methodName);
        m.invoke(obj);


    }
}
