package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//[실버3] DP
public class baekjoon_14501 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] T = new int[N + 1];
		int[] P = new int[N + 1];
		int[] dp = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = N; i >= 1; i--) {
			// 못끝내는 일이면 continue
			if (i + T[i] - 1 > N) {
				if (i == N) {
					continue;
				}
				dp[i] = dp[i + 1];
				continue;
			}
			
			if (i + T[i] - 1 == N) {
				if (i==N) {
					dp[i] = P[i];
					continue;
				}
				dp[i] = Math.max(dp[i + 1],P[i]);
				continue;
			}
			
			if (i + T[i] <= N) {
				dp[i] = Math.max(dp[i + 1], P[i] + dp[i + T[i]]);
				continue;
			}
			
		}
		System.out.println(dp[1]);
	}
}
