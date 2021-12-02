import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2056_dp {

    static int[] dp = new int[10001];
    static int N;
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int time, job;
        String[] input;
        for (int i = 1 ; i <= N ; i++){
            input = br.readLine().split(" ");
            time = Integer.parseInt(input[0]);
            job = Integer.parseInt(input[1]);

            dp[i] = time;

            for (int j = 2 ; j < 2 + job ; j++){
                int temp = Integer.parseInt(input[j]);

                dp[i] = Math.max(dp[i], dp[temp] + time);
            }

            res = Math.max(res, dp[i]);
        }

        System.out.println(res);
    }
}
