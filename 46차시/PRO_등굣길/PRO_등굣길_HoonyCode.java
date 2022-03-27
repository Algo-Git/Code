package PROGRAMMERS;


import java.util.Arrays;

public class PRO_등굣길_HoonyCode {
    class Solution {
        public int solution(int m, int n, int[][] puddles) {
            final int DIV = 1_000_000_007;

            // m, n이 주어진다 m : col n : row;

            int[][] map = new int[n + 1][m + 1];

            for (int i = 1 ; i <= n ; i++){
                Arrays.fill(map[i], Integer.MAX_VALUE);
            }

            // 초기화
            for (int i = 1 ; i <= n ; i++) map[i][1] = 1;
            for (int j = 1 ; j <= m ; j++) map[1][j] = 1;



            //puddles m , n
            int row, col;
            for (int[] puddle : puddles){
                col = puddle[0];
                row = puddle[1];

                if (row == 1){
                    for (int i = col ; i <= m ; i++){
                        map[row][i] = 0;
                    }
                }else if(col == 1){
                    for (int i = row ; i <= n ; i++){
                        map[i][col] = 0;
                    }
                }else{
                    map[row][col] = 0;
                }
            }

            for (int i = 2 ; i <= n ; i++){
                for (int j = 2 ; j <= m ; j++){
                    if (map[i][j] == 0) continue;
                    map[i][j] = ((map[i - 1][j] % DIV) + (map[i][j - 1] % DIV)) % DIV;
                }
            }


            return map[n][m];
        }
    }
}
