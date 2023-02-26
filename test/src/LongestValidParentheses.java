import java.util.Deque;
import java.util.LinkedList;

public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        if(s==null || s.length() == 0){
            return 0;
        }
        int len = s.length();
        int res = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.offerFirst(-1);
        for(int i = 0; i<len; i++){
            if(s.charAt(i) == '('){
                stack.offerFirst(i);
            }else{
                stack.pollFirst();
                if(stack.isEmpty()){
                    stack.offerFirst(i);
                }else{
                    res = i-stack.peekFirst();
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LongestValidParentheses l = new LongestValidParentheses();
        String s = "(()))())(";
        l.longestValidParentheses(s);
    }
}
