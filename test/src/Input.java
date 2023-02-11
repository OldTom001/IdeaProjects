import java.util.Scanner;

public class Input {

    public static void main(String[] args) {
        //单行多组整数输入
/*        Scanner in1=new Scanner(System.in);
        while(in1.hasNext()) {
            int a=in1.nextInt();
            int b=in1.nextInt();
            System.out.println(a+b);
        }*/

        //一维数组
        Scanner scan = new Scanner(System.in);
        int n= scan.nextInt(); //长度
        int[] arr =new int[n];
        for(int i = 0; i<n; i++){
            arr[i] = scan.nextInt();
        }
        for(int i = 0; i<n; i++){
            System.out.println(arr[i]);
        }

        //二维数组输入
       /* Scanner in2 = new Scanner(System.in);
        int m=in2.nextInt();    //行数
        int n=in2.nextInt();    //列数
        int[][] matrix =new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                matrix[i][j]=in2.nextInt();
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }*/

        //字符串
/*        Scanner in3 = new Scanner(System.in);
        String s = in3.next();
        char[] a = s.toCharArray();
        System.out.println(s);*/

        //字符串数组
/*        Scanner scan = new Scanner(System.in);
        int n= scan.nextInt();
        String[] s1=new String[n];
        for (int i = 0; i < n; i++) {
            s1[i]= scan.next();
        }
        for(int i = 0; i<n; i++){
            System.out.println(s1[i]);
        }*/


    }
}
