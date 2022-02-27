import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_16568_엔비스카의영혼 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int a = Integer.parseInt(s[1]);
        int b = Integer.parseInt(s[2]);
        int[] dp = new int[N + 1];

        // 기다렸을 때, a까지 썼을 때, b까지 썼을 때 중 최소 시간 구하기
        for (int i = 1; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;  // 기다렸을 때
            if (i - a - 1 >= 0) dp[i] = Math.min(dp[i], dp[i - a - 1] + 1); // a까지
            if (i - b - 1 >= 0) dp[i] = Math.min(dp[i], dp[i - b - 1] + 1); // b까지
        }

        System.out.print(dp[N]);
    }
}