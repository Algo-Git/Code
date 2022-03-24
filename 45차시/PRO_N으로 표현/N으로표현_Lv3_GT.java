package com.ssafy.programas.DP;

public class N으로표현_Lv3_GT {
    public static void main(String[] args) {
        System.out.println(solution(5, 12));
    }

    static int answer;

    public static int solution(int N, int number) {
        answer = -1;
        dfs(N, number, 0, 0);
        return answer;
    }

    public static void dfs(int N, int number, int sum, int cnt) {
        //최솟값이 8보다 크면 -1을 return 합니다.
        if (cnt > 8)
            return;

        if (sum == number) {
            if (cnt < answer || answer == -1) answer = cnt;
            return;
        }

        int temp = 0;
        for (int i = 1; i < 9; i++) {
            //N, NN, NNN
            temp = temp * 10 + N;
            dfs(N, number, sum + temp, cnt + i);
            dfs(N, number, sum - temp, cnt + i);
            dfs(N, number, sum * temp, cnt + i);
            dfs(N, number, sum / temp, cnt + i);
        }


    }

}
