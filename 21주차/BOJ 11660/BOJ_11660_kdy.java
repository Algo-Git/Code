import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11660_kdy {// BOJ 11660. 구간 합 구하기 5

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 표 크기
		int M = Integer.parseInt(st.nextToken());// 합을 구해야 하는 횟수
		int[][] table = new int[N + 1][N + 1];// 표
		int[][] sum = new int[N + 1][N + 1];// 표의 누적합을 저장할 배열
		for (int i = 1; i <= N; i++) {// 표 입력받기
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 1; i <= N; i++) {// 누적합 구하기
			for (int j = 1; j <= N; j++) {
				sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + table[i][j];
			}
		}
		int x1, x2, y1, y2;
		for (int i = 0; i < M; i++) {// 구간합 구하기
			st = new StringTokenizer(br.readLine());
			// (x1, y1)부터 (x2, y2) 합 구하기
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			// (x1, y1)부터 (x2, y2) 합 출력
			System.out.println(sum[x2][y2] - sum[x2][y1 - 1] - sum[x1 - 1][y2] + sum[x1 - 1][y1 - 1]);
		}
	}

}
