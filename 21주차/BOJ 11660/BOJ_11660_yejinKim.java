import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [실버1] 구간 합 구하기5
// DP
public class BOJ_11660_yejinKim {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] dp = new int[N+1][N+1];
		
		for(int i = 1; i<=N; i++) {
			int pile = 0;
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=N; j++) {
				pile += Integer.parseInt(st.nextToken());
				dp[i][j] = dp[i-1][j] + pile;
			}
		}

		for(int i = 1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			System.out.println(dp[x2][y2]-dp[x1-1][y2]-dp[x2][y1-1]+dp[x1-1][y1-1]);
		}
	}
}
