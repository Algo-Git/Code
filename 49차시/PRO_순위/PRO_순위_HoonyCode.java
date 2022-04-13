package PRO;


import java.util.Arrays;

public class PRO_순위_HoonyCode {

    class Solution {
        public int solution(int n, int[][] results) {
            int answer = 0;

            int[][] map = new int[n + 1][n + 1];
            for (int i = 1 ; i < n + 1; i++) {
                Arrays.fill(map[i], 1000);
            }

            for (int[] result : results) {
                map[result[0]][result[1]] = 1;
            }


            for (int k = 1 ; k < n+1; k++){
                for (int i = 1 ; i < n + 1 ; i++){
                    for (int j = 1;  j < n + 1 ; j++){
                        map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                    }
                }
            }

            int[] cnt = new int[n+1];

            for (int i = 1 ; i < n + 1; i ++){

                for (int j = 1 ; j < n + 1 ; j++){
                    if (map[i][j] != 1000) cnt[i]++;
                    if (map[j][i] != 1000) cnt[i]++;
                }

                if (cnt[i] == n-1) answer++;
            }


            return answer;
        }
    }
}
