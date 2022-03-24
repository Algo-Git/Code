package PROGRAMMERS;

public class PRO_정수삼각형_HoonyCode {
    class Solution {
        public int solution(int[][] triangle) {
            int answer = 0;

            for (int i = triangle.length-2; i > -1; i--) {
                for (int j = 0 ; j < triangle[i].length ; j++){
                    triangle[i][j] += Math.max(triangle[i+1][j] ,triangle[i+1][j+1]);
                }
            }
            answer = triangle[0][0];

            return answer;
        }
    }
}
