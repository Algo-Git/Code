import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_17212_달나라토끼를위한구매대금지불도우미 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());    // 지불할 금액

        int[] money = {2, 5, 7};
        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++){
            dp[i] = i;
        }

        for (int i = 0; i < 3; i++){
            for (int j = money[i]; j <= N; j++){
                dp[j] = Math.min(dp[j], dp[j - money[i]] + 1);
            }
        }

        System.out.print(dp[N]);
    }
}