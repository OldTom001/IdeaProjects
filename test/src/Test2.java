public class Test2 {
    public static void main(String[] args) {
        int[] nums = {2,1,2,2};
        int n = nums.length;
        int count = 0;
        for (int k = 2; k < n; k++) {
            int i = 0, j = k - 1;
            while (i < j) {
                int max = Math.max(nums[i], Math.max(nums[j], nums[k]));
                int min = Math.min(nums[i], Math.min(nums[j], nums[k]));
                if (max - min == 1) {
                    count += j - i;
                    j--;
                } else if (max - min > 1) {
                    i++;
                    j = k - 1;
                } else {
                    j--;
                }
            }
        }
        System.out.println(count);
    }
}
