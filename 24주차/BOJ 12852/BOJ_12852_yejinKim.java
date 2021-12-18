import java.util.Scanner;

// [실버1] 1로 만들기 2 DP
public class BOJ_12852_yejinKim {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int[][] dp = new int[N + 1][2]; // [i][0]에는 연산 횟수를 , [i][1]에는 이전 숫자를 저장
		if (N >= 2) {
			dp[2][0] = 1; // 횟수
			dp[2][1] = 1;// 이전 숫자
		}
		if (N >= 3) {
			dp[3][0] = 1; // 횟수
			dp[3][1] = 1; // 이전 숫자
		}
		for (int i = 4; i <= N; i++) {
			dp[i][0] = 1000;
			if (i % 2 == 0) {
				if (dp[i][0] > dp[i / 2][0]) {
					dp[i][0] = dp[i / 2][0];
					dp[i][1] = i / 2;
				}
			}

			if (i % 3 == 0) {
				if (dp[i][0] > dp[i / 3][0]) {
					dp[i][0] = dp[i / 3][0];
					dp[i][1] = i / 3;
				}
			}

			if (dp[i][0] > dp[i - 1][0]) {
				dp[i][0] = dp[i - 1][0];
				dp[i][1] = i - 1;
			}

			dp[i][0]++;
		}
		int ans = dp[N][0];
		System.out.println(ans);

		for (int i = 0; i < ans; i++) {
			System.out.print(N + " ");
			N = dp[N][1];
		}
		System.out.println(1);
		
		/* dp 아니라 이런식으로 풀면 틀린다. 반례 많음..
		while(true) {
			ans[idx] = N; 
			idx++;
			
			if(N == 1) {
				break;
			}
			
			// 짝수 인 경우
			if(N%2==0) {
				if(N%3==0) {
					N/=3;
					continue;
				}else if((N-1)%3 == 0) {
					N--;
					ans[idx] = N;
					idx++;
					N/=3;
				}else {
					N/=2;
				}
			}else { // 홀수인 경우
				if(N%3 == 0) {
					N/=3;
				}else {
					N--;
				}
			}
		}
		*/
	}
}
