import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_16568_HoonyCode {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int a = Integer.parseInt(in[1]) + 1;
        int b = Integer.parseInt(in[2]) + 1;

        int max = Math.max(a, b);
        int min = Math.min(a, b);

        int[] dp = new int[N + 1];

        Arrays.fill(dp, 1_000_000);
        dp[0] = 0;

        for (int i = 0; i <= N - max; i++) {
            dp[i + max] = Math.min(dp[i + max], dp[i] + 1);
        }
        for (int i = 0; i <= N - min; i++) {
            dp[i + min] = Math.min(dp[i + min], dp[i] + 1);
        }
        for (int i = 0; i < N; i++) {
            dp[i + 1] = Math.min(dp[i + 1], dp[i] + 1);
        }
        System.out.println(dp[N]);

    }

}
