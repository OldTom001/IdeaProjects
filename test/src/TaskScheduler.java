import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        if( n == 0){
            return tasks.length;
        }
        Map<Character, Integer> map = new HashMap<>();
        for(char t : tasks){
            map.put(t, map.getOrDefault(t,0) + 1);
        }
        int taskTypeNum = map.size(); //任务种类数
        List<Integer> nextValid = new ArrayList<>();
        List<Integer> rest = new ArrayList<>();
        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            nextValid.add(1);
            rest.add(entry.getValue());
        }

        int time = 0;
        for(int i = 0; i<tasks.length; i++){
            time++;
            int minNextValid = Integer.MAX_VALUE;
            for(int j = 0; j<taskTypeNum; j++){
                if(rest.get(j) != 0){
                    minNextValid = Math.min(minNextValid, nextValid.get(j));
                }
            }
            time = Math.max(minNextValid, time);
            int maxRestIndex = -1;
            int maxRest = 0;
            for(int j = 0; j<taskTypeNum; j++){
                if(nextValid.get(j) <= time && rest.get(j) != 0){
                    if(rest.get(j) > maxRest){
                        maxRest = Math.max(maxRest, rest.get(j));
                        maxRestIndex = j;
                    }
                }
            }
            System.out.println(i);
            rest.set(maxRestIndex, maxRest-1);
            nextValid.set(maxRestIndex, time+1+n);
        }
        return time;
    }

    public static void main(String[] args) {
        TaskScheduler t = new TaskScheduler();
        char[] tasks = {'A','A','A','A','A','A','B','C','D','E','F','G'};
        int n = 2;
        int res = t.leastInterval(tasks, n);
        System.out.println(res);
    }
}
