package PROGRAMMERS;

public class PRO_N으로표현_HoonyCode {

    class Solution {

        int answer = Integer.MAX_VALUE;

        public int solution(int N, int number) {

            dfs(0, N, number, 0);

            answer = answer > 8 ? -1 : answer;

            return answer;
        }

        private void dfs(int depth, int N, int number, int current) {

            if (depth > 8){
                return;
            }


            if (current == number) {
                answer = Math.min(answer, depth);
                return;
            }

            int temp = 0;

            for (int i = 0 ; i < 8 ; i++){
                if (depth + i < 8) {
                    temp = temp * 10 + N;
                    dfs(depth + i + 1, N, number, current + temp);
                    dfs(depth + i + 1, N, number, current - temp);;
                    dfs(depth + i + 1, N, number, current / temp);;
                    dfs(depth + i + 1, N, number, current * temp);;
                }
            }

        }
    }
}
