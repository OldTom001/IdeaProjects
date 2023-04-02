import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            int n = scanner.nextInt();
            int[] nums = new int[n];
            for(int i = 0; i<n; i++){
                nums[i] = scanner.nextInt();
            }
            int count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    for (int k = j + 1; k < n; k++) {
                        int big = Math.max(nums[i], Math.max(nums[j], nums[k]));
                        int small = Math.min(nums[i], Math.min(nums[j], nums[k]));
                        if(big - small == 1){
                            count++;
                        }
                    }
                }
            }
            System.out.println(count);
        }
    }
}

