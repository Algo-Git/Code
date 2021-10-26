import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11562_kdy {// BOJ 11562. 백양로 브레이크

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());// 건물 수
		int m = Integer.parseInt(st.nextToken());// 길 수
		int[][] map = new int[n][n];// map[a][b]: a->b위해서 양방향 통행으로 바꿔야하는 길 수
		for (int i = 0; i < map.length; i++) {// 최댓값(1000)으로 채우기
			Arrays.fill(map[i], 1000);
		}
		for (int i = 0; i < m; i++) {// 길 정보 입력받기
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken());
			map[u][v] = 0;// u->v 길 있음
			map[v][u] = 1;// v->u이려면 양방향 통행으로 바꾸면 됨
			if (b == 1) {// 양방향 통행인 경우
				map[v][u] = 0;// v->u 길 있음
			}
		}
		// 플로이드-워셜
		for (int k = 0; k < map.length; k++) {
			for (int i = 0; i < map.length; i++) {
				if (i == k) {
					continue;
				}
				for (int j = 0; j < map.length; j++) {
					if (i == j || j == k) {
						continue;
					}
					// i->k에서 바꿔야하는 길 수+k->j의 바꿔야하는 길 수와 i->j일 때 바꿔야하는 길 수 비교해서 최솟값 저장
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
		int k = Integer.parseInt(br.readLine());// 질문 수
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			if (s == e) {// 출/도착지가 같은 경우
				System.out.println(0);// 0 출력
			} else {
				System.out.println(map[s][e]);// 바꿔야하는 길 수 출력
			}
		}
	}
}
