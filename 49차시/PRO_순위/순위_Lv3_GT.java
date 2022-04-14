package com.ssafy.programas.Graph;

/*
플로이드 워셜 >>  a->b 일때, b->c라면 a->c이다

 */
public class 순위_Lv3_GT {
    public static void main(String[] args) {

    }

    public int solution(int n, int[][] results) {
        int answer = 0;
        boolean[][] res = new boolean[n][n];
        for (int i = 0; i < results.length; i++) {
            res[results[i][0] - 1][results[i][1] - 1] = true;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (res[i][k] && res[k][j]) res[i][j] = true;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            int result = 0;
            for (int j = 0; j < n; j++) {
                if (res[i][j] || res[j][i]) // 승패가 있으면
                    result++;
            }
            if (result == n - 1) //순위를 매길수 있으면
                answer++;
        }

        return answer;
    }
}
