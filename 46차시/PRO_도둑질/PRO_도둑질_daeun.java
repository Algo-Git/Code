public class PRO_42897_도둑질 {
    public static void main(String[] args) {

//        System.out.print(Solution.solution(new int[]{1, 2, 3, 1}));
        System.out.print(Solution.solution(new int[]{1000, 1, 0, 1, 2, 1000, 0}));

    }

    static class Solution {
        public static int solution(int[] money) {
            int answer = 0;

            int n = money.length;
            int[] dp = new int[n];

            // 집이 3개이면 3개중 가장 큰 값이 answer
            if (n == 3) {
                answer = Math.max(money[0], Math.max(money[1], money[2]));
            } else {
                // 0번집을 선택했을 때, 마지막집 선택 X
                dp[0] = money[0];   // 0번집까지의 최댓값
                dp[1] = Math.max(money[0], money[1]);   // 1번집까지의 최댓값
                for (int i = 2; i < n - 1; i++) {   // 마지막집 이전까지
                    dp[i] = Math.max(dp[i - 2] + money[i], dp[i - 1]);  // (2번째 전 + 현재), 이전 값 중 최댓값
                }
                answer = Math.max(dp[n - 2], dp[n - 3]);    // 마지막집 이전까지의 최댓값

                // 0번집을 선택하지 않았을 때, 마지막집 선택 O
                dp[0] = 0;  // 0번집까지의 최댓값
                dp[1] = money[1];   // 1번집까지의 최댓값
                for (int i = 2; i < n; i++) {
                    dp[i] = Math.max(dp[i - 2] + money[i], dp[i - 1]);  // (2번째 전 + 현재), 이전 값 중 최댓값
                }
                answer = Math.max(answer, dp[n - 1]);   // 마지막집까지의 최댓값
            }

            return answer;
        }
    }
}