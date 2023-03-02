public class MinPathSum {
    public int minPathSum(int[][] grid) {
        int res = 0;
        int[][] directions = {{0,1}, {1,0}};
        return search(grid, 0, 0, directions, grid[0][0], Integer.MAX_VALUE);
    }

    public int search(int[][] grid, int i, int j, int[][] directions, int sum, int res){

        if(i == grid.length-1 && j == grid[0].length-1){
            res = Math.min(res, sum);
            return res;
        }
        if(i<grid.length-1 && j <grid[0].length-1){
            for(int d = 0; d<directions.length; d++){
                int iNew = i+directions[d][0];
                int jNew = j+directions[d][1];

                res = search(grid, iNew, jNew, directions, sum+grid[iNew][jNew], res);

            }

        }
        return res;
    }

    public static void main(String[] args) {
        MinPathSum m = new MinPathSum();
        int[][] grid= {{1,3,1},{1,5,1},{4,2,1}};
        int res = m.minPathSum(grid);
        System.out.println(res);
    }
}
