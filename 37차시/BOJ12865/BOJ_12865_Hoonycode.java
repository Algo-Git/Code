import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_12865_Hoonycode {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().trim().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        int[] weight = new int[N+1];
        int[] profit = new int[N+1];
        for (int i = 1 ; i <= N ; i++){
            input = br.readLine().trim().split(" ");
            weight[i] = Integer.parseInt(input[0]);
            profit[i] = Integer.parseInt(input[1]);
        }

        int[] dp = new int[K+1];

        for (int i = 1 ; i <= N ; i++){
            for (int w = K ; w >= weight[i] ; w--){
                dp[w] = Math.max(dp[w], dp[w-weight[i]] + profit[i]);
            }
        }

        System.out.println(dp[K]);
    }
}