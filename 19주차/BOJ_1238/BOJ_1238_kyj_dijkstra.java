import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// x에서 시작해서 정방향 길 따라가면서 집에 돌아가는 최솟값
// x에서 시작해서 역방향 길 따라가면서 집으로 가는 최솟값
public class BOJ_1238_kyj_dijkstra {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		// 인접 매트리스
		int[][] map = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = 1000000; // 큰 수로 초기화
			}
			map[i][i] = 0;
		}

		// 맵 채우기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			map[start][end] = time;
		}

		// 나->나 는 거리 0
		for (int i = 1; i <= N; i++) {
			map[i][i] = 0;
		}

		int[] HomeToX = new int[N + 1]; // 집에서 x까지 가는 것은 = X에서 출발지까지 가는 것 역방향
		int[] XtoHome = new int[N + 1]; // X에서 집까지 가는 것 정방향

		for (int i = 1; i <= N; i++) {
			HomeToX[i] = 1000000;
			XtoHome[i] = 1000000;
		}

		HomeToX[X] = 0; // 시작점
		XtoHome[X] = 0; // 끝점

		int cnt = 0; // 방문 정점 개수
		boolean[] visited = new boolean[N + 1];

		// 정방향 길 따라가기 XtoHome
		while (true) {
			if (cnt == N) {
				break;
			}
			int min = 1000000;
			int k = -1; // 경유지
			// 최소값을 시작점으로
			for (int i = 1; i <= N; i++) {
				if (visited[i]) { // 방문하지 않았고
					continue;
				}
				if (min > XtoHome[i]) { // d값이 최소인 곳을 경유지로 정한다.
					min = XtoHome[i];
					k = i;
				}
			}

			cnt++; // 고려한 개수
			visited[k] = true; // 고려한 곳은 true

			// 경유지를 거쳐서 갈 수 있는 곳은, 경유하는 경우와 원래 최소값(d)를 비교한다.
			for (int i = 1; i <= N; i++) {
				// 연결 안되어있으면
				if (map[k][i] == 1000000) {
					continue;
				}
				// 연결되어 있으면
				if (XtoHome[k] + map[k][i] < XtoHome[i]) {
					XtoHome[i] = XtoHome[k] + map[k][i];
				}
			}
		}

		// 역방향 길 따라가기 HomeToX
		cnt = 0;
		visited = new boolean[N+1];
		while (true) {
			if (cnt == N) {
				break;
			}
			
			int min = 1000000;
			int k = -1; // 경유지
			// 최소값을 시작점으로
			for (int i = 1; i <= N; i++) {
				if (visited[i]) { // 방문하지 않았고
					continue;
				}
				if (min > HomeToX[i]) { // d값이 최소인 곳을 경유지로 정한다.
					min = HomeToX[i];
					k = i;
				}
			}

			cnt++; // 고려한 개수
			visited[k] = true; // 고려한 곳은 true

			// 경유지를 거쳐서 갈 수 있는 곳은, 경유하는 경우와 원래 최소값(d)를 비교한다.
			// 역방향 인덱스 주의
			for (int i = 1; i <= N; i++) {
				// 연결 안되어있으면
				if (map[i][k] == 1000000) {
					continue;
				}
				// 연결되어 있으면
				if (HomeToX[k] + map[i][k] < HomeToX[i]) {
					HomeToX[i] = HomeToX[k] + map[i][k];
				}
			}
		}

		int max = 0;
		for (int i = 1; i <= N; i++) {
			if (XtoHome[i] + HomeToX[i] > max) {
				max = XtoHome[i] + HomeToX[i];
			}
		}

		System.out.println(max);
	}

}
