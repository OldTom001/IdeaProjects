
import java.util.Scanner;

public class dc {

    public static void main(String[] args) {
        Input i = new Input();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            long curr = scanner.nextLong();
            long odd = 0; //奇数
            long even = 0; //偶数
            int oddCount = 0;
            int evenCount = 0;
            while(curr>0){
                long n = curr;
                curr /= 10;
                int bit = (int)(n - curr*10);
                if(bit %2 == 1){ //奇数
                    odd = odd + bit*(long)Math.pow(10, oddCount);
                    oddCount++;
                }else{
                    even = even + bit*(long)Math.pow(10, evenCount);
                    evenCount++;
                }
            }
            System.out.println(Math.abs(odd-even));
        }
    }
}
