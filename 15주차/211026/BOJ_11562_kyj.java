package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [골드4] 백양로 브레이크
public class BOJ_11562_kyj {
	static int n, m;
	static int[][] adjMat;
	static int s, e;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 건물 수
		m = Integer.parseInt(st.nextToken()); // 길 수

		adjMat = new int[n + 1][n + 1];
		for(int i = 1; i<=n; i++) {
			for(int j = 1; j<=n; j++) {
				adjMat[i][j] = 1000;
				if(i==j) {
					adjMat[i][j] = 0;
				}
			}
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int mode = Integer.parseInt(st.nextToken());
			adjMat[from][to] = 0;
			if (mode == 1) {
				adjMat[to][from] = 0;
			} else {
				adjMat[to][from] = 1;
			}
		}


		// 플로이드 와샬 경 출 도
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) { // 출
				if (i == k)
					continue;
				for (int j = 1; j <= n; j++) { // 도
					if (j == i)
						continue;

					if(adjMat[i][k] + adjMat[k][j] < adjMat[i][j]) {
						adjMat[i][j] = adjMat[i][k] + adjMat[k][j];
					}
				}
			}
		}
		
		int Q = Integer.parseInt(br.readLine());
		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken()); // 시작점
			e = Integer.parseInt(st.nextToken()); // 끝점
			System.out.println(adjMat[s][e]);
		}
	}
}
