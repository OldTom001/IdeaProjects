import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix==null || matrix.length == 0){
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        int boundLeft = -1;
        int boundRight = matrix[0].length;
        int boundTop = -1;
        int boundBottom = matrix.length;
        int i = 0, j = 0;//行,列
        int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};//右下左上四个方向
        int d = 0;
        res.add(matrix[0][0]);
        while(true){

            if(j+directions[d][1] < boundRight && j+directions[d][1] > boundLeft && i+directions[d][0] < boundBottom && i+directions[d][0] > boundTop){
                //保持当前方向

            }else if(j+directions[(d+1)%4][1] < boundRight && j+directions[(d+1)%4][1] > boundLeft && i+directions[(d+1)%4][0] < boundBottom && i+directions[(d+1)%4][0] > boundTop){
                //转向
                switch (d){
                    case 0:
                        boundTop++;
                        break;
                    case 1:
                        boundRight--;
                        break;
                    case 2:
                        boundRight--;
                        break;
                    case 3:
                        boundLeft++;
                        break;
                    default:
                        break;
                }

                d = (d+1)%4;

            }else{
                return res;
            }
            i = i+directions[d][0];
            j = j+directions[d][1];
            System.out.print("i:" + i);
            System.out.println(" j:" + j);
            res.add(matrix[i][j]);
        }
    }

    public static void main(String[] args) {
        int martrix[][] ={{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        SpiralMatrix s = new SpiralMatrix();
        s.spiralOrder(martrix);

    }
}
