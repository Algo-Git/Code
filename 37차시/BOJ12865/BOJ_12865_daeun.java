import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12865_평범한배낭 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 물품의 수
        int K = Integer.parseInt(st.nextToken());   // 배낭 최대 무게
        int[] W = new int[N];   // 물건 무게
        int[] V = new int[N];   // 물건 가치
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());   // 물건 무게
            V[i] = Integer.parseInt(st.nextToken());    // 물건 가치
        }

        int[] dp = new int[K + 1];

        for (int i = 0; i < N; i++) {
            for (int j = K; j >= 1; j--) {  // 1부터 K까지하면 : 이전에 바뀐값으로 계산하므로 중복됨
                if (j >= W[i]) dp[j] = Math.max(dp[j], dp[j - W[i]] + V[i]);
            }
        }

        System.out.print(dp[K]);
    }
}