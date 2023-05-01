import java.util.Arrays;
import java.util.Comparator;

public class sortTest {
    public static void main(String[] args) {

        int[] nums = {1,2,3,4,5,6};
        int target = 3;
        System.out.println(binarySearch(nums,target));


    }
    public static int binarySearch(int[] nums, int target){
        int left = 0, right = nums.length-1;
        int mid = 0;
        while (left<=right){
            mid = left + ((right-left)>>1);
            if(nums[mid] <= target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        if(left> nums.length-1){
            return -1;
        }
        return nums[left];
    }
}
