public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        int len1 = s.length(), len2 = p.length();
        boolean[][] dp = new boolean[len1+1][len2+1]; //下标表示低i个字符
        dp[0][0] = true;
        for(int i = 0; i<=len1; i++){
            for(int j = 1; j<=len2; j++){
                if(p.charAt(j-1) != '*'){
                    dp[i][j] = match(s,p,i,j) ? dp[i-1][j-1] : false;
                }else{
                    if(match(s,p,i,j-1) && (dp[i][j-2]||dp[i-1][j])){
                        dp[i][j] = true;
                    }else{
                        dp[i][j] = dp[i][j-2];
                    }
                }
            }
        }
        return dp[len1][len2];
    }
    public boolean match(String s, String p, int i, int j) {
        //判断s[i]和p[j]是否匹配
        if(i == 0){
            return false;
        }
        if (p.charAt(j-1) == '.') {
            return true;
        }
        if (p.charAt(j-1) == s.charAt(i-1)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        RegularExpressionMatching r = new RegularExpressionMatching();
        String s = "aab";
        String p = "c*a*b";
        System.out.println( r.isMatch(s,p));
    }

}
