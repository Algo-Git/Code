import java.util.Scanner;

// [골드5] 홀수 홀릭 호석
// N이 최대 99999999 니까 재귀 돌려도 터지지 않을 것이라고 생각

// 3자리수 이상이면 조합이용해서 숫자문자열 나누기=>2중 for
// 끊을 수의 자릿수-1 개의 칸에서 2개 고르기
public class BOJ_20164_yejinKim {
	static int Min, Max;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Max = -1;
		Min = Integer.MAX_VALUE;
		dfs(N, 0);
		System.out.println(Min + " " + Max);
	}

	private static void dfs(int num, int cnt) {
		int localCnt = 0;
		if (num < 10) {
			localCnt = num % 2;
			Max = Math.max(cnt + localCnt, Max);
			Min = Math.min(cnt + localCnt, Min);
			return;
		} else if (num < 100) {
			localCnt += num % 2;
			localCnt += (num / 10) % 2;
			dfs(num%10 + num/10, cnt + localCnt);
		} else {
			int k = (int) Math.log10(num); // 반복 횟수, 칸의 개수
			for (int i = 0; i <= k; i++) {
				localCnt += (num / Math.pow(10, i)) % 2;
			}
			String stringNum = Integer.toString(num);
			for (int i = 1; i <= k; i++) {
				for (int j = i + 1; j <= k; j++) {
					dfs(Integer.parseInt(stringNum.substring(0, i)) + Integer.parseInt(stringNum.substring(i, j))
							+ Integer.parseInt(stringNum.substring(j)), cnt + localCnt);
				}
			}
		}
	}
}
