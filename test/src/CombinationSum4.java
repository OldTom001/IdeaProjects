public class CombinationSum4 {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        for(int j = 1; j<=target;j++){
            for(int i = 0; i<nums.length; i++){
                if(j>=nums[i]){
                    dp[j] += dp[j-nums[i]];
                }
                System.out.print(dp[j] + " ");
            }
            System.out.println();
        }
        return dp[target];
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        int target = 4;
        CombinationSum4 c = new CombinationSum4();
        c.combinationSum4(nums, target);
    }
}
