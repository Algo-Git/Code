public class PRO_42898_등굣길 {
    public static void main(String[] args) {

        System.out.print(Solution.solution(4, 3, new int[][]{{2, 2}}));

    }

    static class Solution {
        public static int solution(int m, int n, int[][] puddles) {
            int answer = 0;

            int[][] dp = new int[n + 1][m + 1];

            int len = puddles.length;

            // 장애물 -1 체크
            for (int i = 0; i < len; i++) {
                dp[puddles[i][1]][puddles[i][0]] = -1;
            }

            // 첫 행에서 장애물을 만나기 전까지 1 입력 (장애물 만난 이후에는 갈 방법이 0이므로 이전까지만)
            for (int i = 2; i <= m; i++) {
                if (dp[1][i] == -1) break;
                dp[1][i] = 1;
            }

            // 첫 열에서 장애물을 만나기 전까지 1 입력 (장애물 만난 이후에는 갈 방법이 0이므로 이전까지만)
            for (int i = 2; i <= n; i++) {
                if (dp[i][1] == -1) break;
                dp[i][1] = 1;
            }

            for (int i = 2; i <= n; i++) {
                for (int j = 2; j <= m; j++) {
                    if (dp[i][j] == -1) continue;   // 장애물 위치이면 continue
                    if (dp[i - 1][j] != -1) dp[i][j] += dp[i - 1][j];   // 위가 장애물이 아니면
                    if (dp[i][j - 1] != -1) dp[i][j] += dp[i][j - 1];   // 왼쪽이 장애물이 아니면
                    dp[i][j] = dp[i][j] % 1_000_000_007;    // 1_000_000_007 로 나눈 나머지
                }
            }

            answer = dp[n][m];

            return answer;
        }
    }
}