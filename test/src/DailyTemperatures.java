import java.util.LinkedList;
import java.util.Queue;

public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        Queue<Integer> que = new LinkedList<>();
        int[] res = new int[temperatures.length];
        que.offer(0);
        for(int i = 1; i<temperatures.length; i++){
            if(que.isEmpty() || temperatures[i] <= temperatures[que.peek()]){
                que.offer(i);
            }
            else{
                while(!que.isEmpty() && temperatures[i] > temperatures[que.peek()]){
                    int index = que.poll();
                    res[index] = i-index;
                }
                que.offer(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] tem = new int[]{73,74,75,71,69,72,76,73};
        DailyTemperatures d = new DailyTemperatures();
        d.dailyTemperatures(tem);

    }
}
