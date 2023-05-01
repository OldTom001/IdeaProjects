import java.util.Scanner;
import java.util.concurrent.BlockingDeque;

public class Comb4 {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        double k=in.nextDouble();
        double n=in.nextDouble();
//        System.out.println(Combination(n,k));
        System.out.println(Combination1(n,k));
    }

    //数学公式法
    private static double Combination1(double n, double k) {
        double a=1,b=1;
        if(k>n/2) {
            k=n-k;
        }
        for(int i=1;i<=k;i++) {
            a*=(n+1-i);
            b*=i;
        }
        return a/b;
    }

    //递归法
    private static int Combination(int n, int k) {
        if(k==0||k==n)
            return 1;
        else
            return Combination(n-1, k)+Combination(n-1, k-1);
    }

}
