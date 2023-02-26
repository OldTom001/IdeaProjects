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
    //手写开根号, 精确到0.1
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
        System.out.println("答案:" + (float)(temp)/10);

        System.out.println("二分: " + res);
    }
    @Test
    public void testFindMedianSortedArrays(){
        int[] nums1 = {1};
        int[] nums2 = {2,3,4,5,6};
        double res = findMedianSortedArrays(nums1, nums2);
        System.out.println(res);
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double res;
        int length1 = nums1.length;
        int length2 = nums2.length;
        if((nums1.length + nums2.length) % 2 ==1){
            res = findKthElement(nums1, nums2, (length1+length2)/2 +1);//(length1+length2)/2是中位数索引, 需要+1
        }else{
            res = (findKthElement(nums1, nums2, (length1+length2)/2) + findKthElement(nums1, nums2, (length1+length2)/2 + 1))/2;//
        }
        return res;
    }
    //寻找第k小的元素, k从1开始记
    public double findKthElement(int[] nums1, int[] nums2, int k) {
        // k = 1
        int length1 = nums1.length; //0
        int length2 = nums2.length; //1
        int index1 = 0, index2 = 0;

        while (true) {

            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }
            int half = k / 2;
            int targetIndex1 = Math.min(length1, index1 + half) - 1;
            int targetIndex2 = Math.min(length2, index2 + half) - 1;
            if (nums1[targetIndex1] <= nums2[targetIndex2]) {
                k -= (targetIndex1 - index1 + 1);
                index1 = targetIndex1 + 1;

            } else {
                k -= (targetIndex2 - index2 + 1);
                index2 = targetIndex2 + 1;

            }
        }
    }
}
