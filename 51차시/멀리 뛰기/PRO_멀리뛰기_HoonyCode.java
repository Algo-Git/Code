package PRO;

public class PRO_멀리뛰기_HoonyCode {

    class Solution {
        public long solution(int n) {
            long answer = 0;

            // dp 문제인것 같다
            final int div = 1234567;

            //n은 1이상 2000이하인 정수
            int[] dp = new int[2001];

            dp[1] = 1;
            dp[2] = 2;

            for (int i = 3; i <= n ; i++){
                dp[i] = (dp[i-1] + dp[i-2])%div;
            }

            answer = dp[n];

            return answer;
        }
    }
}
