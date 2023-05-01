public class strStr {
    public static int strStr(String haystack, String needle) {

        if(needle.length() > haystack.length()){
            return -1;
        }
        if(haystack.length() == 0 && needle.length() == 0){
            return 0;
        }

        int needleIndex = 0;
        int haystackIndex = 0;
        for(int i = 0; i<haystack.length(); i++){
            needleIndex = 0;
            haystackIndex = i;
            int j = 0;
            for(j = 0; j<needle.length(); j++){
                if(needle.charAt(j)!=haystack.charAt(haystackIndex)){
                    break;
                }
                haystackIndex++;
            }
            if(j == needle.length()){
                return i;
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        String haystack = "mississippi";
        String needle = "issipi";
        System.out.println(strStr(haystack, needle));

    }

}
