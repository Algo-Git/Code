import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N17212 {// BOJ 17212. 달나라 토끼를 위한 구매대금 지불 도우미

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());// 지불해야하는 금액
        int[] dp = new int[Math.max(N + 1, 8)];// 합법적으로 낼 수 있는 동전의 수(최소 7원부터)
        for (int i = 1; i < 5; i++) {// 1~4원까지 합법이 되는 동전 개수 입력
            dp[i] = i / 2 + (i & 1);// 2원짜리 동전과 1원짜리 동전으로 지불
        }
        // 5~7원 합법적인 동전 수
        dp[5] = 1;
        dp[6] = 2;
        dp[7] = 1;
        for (int i = 8; i <= N; i++) {// 8원부터 N원까지 합법적으로 낼 수 있는 동전 수 구하기
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + 1;// 1원과 2원을 사용한 경우 중 최소 동전 수
            dp[i] = Math.min(dp[i], Math.min(dp[i - 5], dp[i - 7]) + 1);// 1, 2, 5, 7원을 사용하는 경우 중 최소 동전 수
        }
        System.out.println(dp[N]);// N원을 합법적으로 지불하기 위해 필요한 동전 개수 출력
    }
}
