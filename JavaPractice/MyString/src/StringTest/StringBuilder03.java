package StringTest;
/*构建方法, 将数组拼接成字符串*/
public class StringBuilder03 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        String s = arrayToString(arr);
        System.out.println("s: " + s);
    }
    public static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < arr.length; i++) {
            if(i == arr.length - 1) {
                sb.append(arr[i]);
            } else {
                sb.append(arr[i]);
                sb.append(", ");
            }
        }
        sb.append("]");
        String s = sb.toString();
        return s;
    }
}
