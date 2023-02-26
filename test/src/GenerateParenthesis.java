import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    public List<String> generateParenthesis(int n) {
        StringBuilder temp = new StringBuilder();
        List<String> res = new ArrayList<>();
        backTracking(n, 0, 0, temp, res);
        return res;
    }
    public void backTracking(int n, int leftCount, int rightCount, StringBuilder temp, List<String> res){
        if(temp.length() == 2*n){
            res.add(temp.toString());
            return;
        }
        if(leftCount<n){
            temp.append("(");
            backTracking(n, leftCount+1, rightCount, temp, res);
            temp.deleteCharAt(temp.length()-1);
        }
        if(leftCount-rightCount>0){
            temp.append(")");
            backTracking(n, leftCount, rightCount+1, temp, res);
            temp.deleteCharAt(temp.length()-1);
        }
    }

    public static void main(String[] args) {
        GenerateParenthesis g = new GenerateParenthesis();
        int n = 3;
        List<String> res = g.generateParenthesis(n);
        System.out.println(res);
    }
}
