package StringTest;
/*使用append和reverse方法进行字符串构建与反转
public StringBuilder append(任意类型): 添加数据, 返回本身
public StringBuilder reverse(): 返回相反的字符序列*/
public class StringBuilder02 {
    public static void main(String[] args) {
         StringBuilder sb = new StringBuilder();
       /* StringBuilder sb2 = sb.append("Hello");
        System.out.println("sb: " + sb);
        System.out.println("sb2: " + sb2);
        System.out.println(sb == sb2);*/

/*        sb.append("Hello");
        sb.append("World");
        sb.append("12345");
        sb.append("++--");
        System.out.println("sb: " + sb);*/

//      链式编程
        sb.append("Hello").append("World").append("12345").append("++--");
        System.out.println("sb: " + sb);
//      反转方法
        sb.reverse();
        System.out.println("sb: " + sb);
    }
}
