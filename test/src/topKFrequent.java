import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class topKFrequent {
    public static int[] topKFrequent(int[] nums, int k) {

        Map<Integer,Integer> count = new HashMap<>();
        int[] res = new int[k];
        for(int i = 0; i<nums.length; i++){
            count.put(nums[i], count.getOrDefault(nums[i],0) + 1);
        }
        List<int[]> list = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : count.entrySet()){
            list.add(new int[]{entry.getKey(), entry.getValue()});
        }
        qSort(list, 0, list.size()-1, k, res, 0);

        return res;
    }
    public static void qSort(List<int[]> list , int left, int right, int k, int[] res, int resIndex){
        //int partitionIndex = (new Random().nextInt(right-left+1)) + left;
        int partitionIndex = right;
        int partition = list.get(partitionIndex)[1];
        Collections.swap(list, left, partitionIndex);

        int leftIndex = left; //大于等于区最后一个元素索引
        for(int i = left+1; i<=right; i++){
            if(list.get(i)[1] >= partition){
                Collections.swap(list, i, ++leftIndex);
            }
        }
        Collections.swap(list, left, leftIndex);
        int leftLen = leftIndex - left + 1; //大于等于区长度，包含分割点
        if(leftLen == k){
            for(int i = left; i<=leftIndex; i++){
                res[resIndex++] = list.get(i)[0];
            }
            return;
        }
        if(leftLen > k){
            qSort(list, left, leftIndex, k, res, resIndex);
        }else if(leftLen < k){
            for(int i = left; i<=leftIndex; i++){
                res[resIndex++] = list.get(i)[0];
            }
            qSort(list, leftIndex+1, right, k-leftLen, res, resIndex);

        }
    }

    public static void main(String[] args) {
        int[] nums = {4,1,-1,2,-1,2,3};
        int k = 2;
        int[] res = topKFrequent(nums, k);
        for(int r : res){
            System.out.println(r);
        }

    }
}
