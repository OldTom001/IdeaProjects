package ToStringTest;

public class toStringDemo {
    public static void main(String[] args) {
        Student s = new Student("彭于晏", 30);
//        println中最终调用了Object类中的toString方法, 现在在Student类中重写了toString方法,
//        因此直接将s赋值给println即可按照重写后的格式输出.
        System.out.println(s);
    }
}
