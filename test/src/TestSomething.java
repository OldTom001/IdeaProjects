import org.junit.Test;

import static java.lang.StrictMath.floor;
import static java.lang.StrictMath.sqrt;

public class TestSomething {
    @Test
    public void testCombinationSum4(){
        float a = 1;
        System.out.println(a);
        CombinationSum4 combinationSum4 = new CombinationSum4();
        int[] nums = {1,2,3};
        int res = combinationSum4.combinationSum4(nums,4);
        System.out.println(res);
    }
    @Test
    public void mySort(){
        int x = 10;
        float left =0, right = x;
        float res = -1;
        while (left<=right){
            float mid = left+(right-left)/2;
            int temp = (int)(mid * 10);
            mid = (float)temp/10;
            if(mid * mid <=x){
                res = mid;
                left = mid+0.1f;
            }else{
                right = mid - 0.1f;
            }
        }
        double solution = sqrt(x);
        int temp = (int)(solution*10);
        System.out.println("´ð°¸:" + (float)(temp)/10);

        System.out.println("¶þ·Ö: " + res);
    }
}
