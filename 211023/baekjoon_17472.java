package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// [골드2] 다리만들기 2
// 1. 섬의 개수 구하기
// 2. 섬사이에 연결할 수 있는지, 길이 구하기
// 3. 모든 섬이 연결되는 최소 비용 구하기 - MST
public class BOJ_17472_kyj {
	static int N, M, islandCnt;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[][] dist;
	static int[] d;
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = -(Integer.parseInt(st.nextToken()));
			}
		}

		// 섬 구분하고 개수세기
		islandCnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == -1) {
					islandCnt++;
					bfs(i, j, islandCnt);
				}
			}
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}

		dist = new int[islandCnt + 1][islandCnt + 1];
		for (int i = 1; i <= islandCnt; i++) {
			for (int j = 1; j <= islandCnt; j++) {
				dist[i][j] = INF;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					continue;
				}

				for (int d = 0; d < 4; d++) {
					int step = 0;
					while (true) {
						step++;
						int nx = i + step * dx[d];
						int ny = j + step * dy[d];

						if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
							break;
						}

						if (map[nx][ny] == map[i][j]) {
							break;
						}

						if (map[nx][ny] == 0) {
							continue;
						}

						// 다른 다리랑 만난다면
						// 만약 길이가 1이라면
						if (step == 2) {
							break;
						}
						dist[map[nx][ny]][map[i][j]] = Math.min(dist[map[nx][ny]][map[i][j]], step - 1);
						dist[map[i][j]][map[nx][ny]] = Math.min(dist[map[i][j]][map[nx][ny]], step - 1);
						break;
					}
				}
			}
		}

		// 연결 완료

//		for (int i = 1; i <= islandCnt; i++) {
//			for (int j = 1; j <= islandCnt; j++) {
//				System.out.print(dist[i][j] + " ");
//			}
//			System.out.println();
//		}

		// 이제 최소 연결 구현
		// 초기화
		d = new int[islandCnt + 1];
		for (int i = 1; i <= islandCnt; i++) {
			d[i] = INF;
		}

		System.out.println(MST());

	}

	private static int MST() {
		boolean[] visited = new boolean[islandCnt + 1];
		int connected = 0;
		int ans = 0;
		d[1] = 0;
		
		while (true) {
//			System.out.println(Arrays.toString(d));
			if(connected == islandCnt) {
				return ans;
			}
			
			int min = INF;
			int idx = 0;
			for (int i = 1; i <= islandCnt; i++) {
				if (min > d[i] && !visited[i]) {
					min = d[i];
					idx = i;
				}
			}
			ans += min;
			if(min == INF) {
				return -1;
			}
			visited[idx] = true;
			connected ++;
			// 거리 업데이트
			for (int j = 1; j <= islandCnt; j++) {
				if(!visited[j] && dist[idx][j] != INF) {
					d[j] = Math.min(dist[idx][j], d[j]);
				}
			}
		}
	}

	private static void bfs(int x, int y, int num) {
		Queue<Data> q = new LinkedList<>();
		map[x][y] = num;
		q.offer(new Data(x, y));

		while (!q.isEmpty()) {
			Data data = q.poll();

			for (int d = 0; d < 4; d++) {
				int nx = data.x + dx[d];
				int ny = data.y + dy[d];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
					continue;
				}

				if (map[nx][ny] == -1) {
					map[nx][ny] = num;
					q.offer(new Data(nx, ny));
				}
			}
		}
	}

	private static class Data {
		int x;
		int y;

		public Data(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
