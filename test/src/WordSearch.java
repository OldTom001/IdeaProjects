public class WordSearch {
    public boolean exist(char[][] board, String word) {

        int h = board.length;
        int w = board[0].length;
        int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        boolean[][] visited = new boolean[h][w];
        for(int i = 0; i<h; i++){
            for(int j = 0; j<w ;j++){
                boolean flag = check(board, word, i, j, 0, directions, visited);
                if(flag){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean check(char[][] board, String word, int i, int j, int k, int[][] directions, boolean[][] visited){

        if(board[i][j] != word.charAt(k)){
            return false;
        }else if(k == word.length()-1){
            return true;
        }

        for(int[] d: directions){
            int iNew = i + d[0];
            int jNew = j + d[1];
            if(iNew >=0 && iNew<board.length && jNew>=0 && jNew<board[0].length){
                if(!visited[iNew][jNew]){
                    visited[iNew][jNew] = true;
                    boolean flag = check(board, word, iNew, jNew, k+1, directions, visited);
                    if(flag){
                        return true;
                    }
                    visited[iNew][jNew] = false;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {{'a','a'}};
        String word = "aaa";
        WordSearch w = new WordSearch();
        boolean res = w.exist(board, word);
        System.out.println(res);
    }
}
