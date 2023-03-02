public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = len-1;
        while(left <= right){
            int mid = left + ((right - left)>>1);
            if(nums[mid] >= nums[left]){
                if(target >= nums[left] && target <= nums[mid]){
                    if(target == nums[mid]){
                        return mid;
                    }
                    else{
                        right = mid - 1;
                    }
                }else{
                    left = mid + 1;
                }

            }else if(nums[mid] <= nums[right] ){
                if(target >= nums[mid] && target <= nums[right]){
                    if(target == nums[mid]){
                        return mid;
                    }else{
                        left = mid + 1;
                    }
                }else{
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        SearchInRotatedSortedArray s = new SearchInRotatedSortedArray();
        int res = s.search(nums, target);
        System.out.println(res);

    }
}
