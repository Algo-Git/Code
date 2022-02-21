import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17212_HoonyCode {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int money = Integer.parseInt(br.readLine());
        int[] dp = new int[100_001];

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;
        dp[4] = 2;
        dp[5] = 1;
        dp[6] = 2;
        dp[7] = 1;

        for (int i = 8 ; i < 100_001; i++){
            dp[i] = Math.min(dp[i-7] + 1, dp[i-5] + 1);
            dp[i] = Math.min(dp[i], dp[i-1] + 1);
            dp[i] = Math.min(dp[i], dp[i-2] + 1);
        }

        System.out.print(dp[money]);

    }
}
