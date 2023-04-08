
import java.util.Scanner;

public class dc {
    static boolean[][] visited;
    static char[][] color;
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       int n = scanner.nextInt();
       int m = scanner.nextInt();
       scanner.nextLine();
       color = new char[n][m];
       int[][] res = new int[n][m];
       for(int i = 0; i<n; i++){
           color[i] = scanner.nextLine().toCharArray();
       }
       visited  = new boolean[n][m];
       int redCountOrigin = 0;
       for(int i =0 ;i<n; i++){
           for(int j = 0; j<m; j++){
               if(color[i][j] =='R' && !visited[i][j]){
                   redCountOrigin++;
                   visited[i][j] = true;
                   dfs(i,j, n, m);
               }
           }
       }
       for(int k = 0; k<n; k++){
           for(int h = 0; h<m; h++){
               if(color[k][h] =='W'){
                   res[k][h] = redCountOrigin;
               }else{
                   visited = new boolean[n][m];
                   int redCount = 0;
                   color[k][h] ='W';
                   for(int i =0 ;i<n; i++){
                       for(int j = 0; j<m; j++){
                           if(color[i][j] =='R' && !visited[i][j]){
                               redCount++;
                               visited[i][j] = true;
                               dfs(i,j, n, m);
                           }
                       }
                   }
                   color[k][h] ='R';
                   res[k][h] = redCount;
               }
           }
       }
       for(int i = 0; i<n; i++){
           for(int j = 0; j<m; j++){
               System.out.print(res[i][j]);
               if(j!=m-1){
                   System.out.print(" ");
               }
           }
           if(i!= n-1){
               System.out.println();
           }
       }
    }
    public static void dfs(int x, int y, int n, int m){
        int[] dx = {-1,1,0,0};// 上下左右 行索引
        int[] dy = {0,0,-1,1};//列索引
        for(int i = 0; i<4; i++){
            int newX = x+dx[i];
            int newY = y+dy[i];
            if(newX<0 || newX>=n || newY<0 || newY >=m || visited[newX][newY] || color[newX][newY] == 'W'){
                continue;
            }
            visited[newX][newY] = true;
            dfs(newX, newY, n, m);
        }

    }
}
