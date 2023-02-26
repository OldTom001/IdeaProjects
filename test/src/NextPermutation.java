public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        for(int i = len-1; i>0; i--){
            if(nums[i]>nums[i-1]){
                for(int j = len-1; j>i-1; j--){
                    if(nums[j]>nums[i-1]){
                        int temp = nums[i-1];
                        nums[i-1] = nums[j];
                        nums[j] = temp;
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        NextPermutation n = new NextPermutation();
        n.nextPermutation(nums);
    }
}
