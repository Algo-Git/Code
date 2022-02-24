import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N12865 {// BOJ 12865. 평범한 배낭
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());// 물품의 수
        int K = Integer.parseInt(st.nextToken());// 준서가 버틸 수 있는 무게

        int[][] dp = new int[N + 1][K + 1];// N번째 물품까지 고려했을 때 K무게만큼 물건을 넣었을 때 가치 최댓값
        for (int i = 1; i <= N; i++) {// 물품 정보 입력받기
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());// 물품의 무게
            int V = Integer.parseInt(st.nextToken());// 물품의 가치
            for (int j = 1; j <= K; j++) {// 가치 최댓값 구하기
                if (j < W)// 현재 물품을 넣을 수 없는 경우
                    dp[i][j] = dp[i - 1][j];// 이전 물품까지 고려한 가치 입력
                else// 현재 물품을 넣을 수 있는 경우
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - W] + V);// 현재 물품을 넣은 경우와 아닌 경우 중 최대 가치 입력
            }
        }
        System.out.println(dp[N][K]);// 가치 최댓값 출력
    }
}