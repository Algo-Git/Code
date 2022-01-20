import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N2573 {// 2573. 빙산

	static int N, M, sum;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 행
		M = Integer.parseInt(st.nextToken());// 열
		int[][] ice = new int[N][M];// 빙산 높이 정보 배열
		sum = 0;// 다 녹았는지 확인
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				ice[i][j] = Integer.parseInt(st.nextToken());// 빙산 높이 입력받기
				sum += ice[i][j];// 녹지않은 빙산이 있으면 더하기
			}
		}
		int ans = 0;// 녹아서 두 덩어리 이상이 되는데 걸리는 시간
		int time = 0;// 녹는 시간
		while (sum != 0) {// 다 녹을때까지
			time += 1;// 1초 지남
			ice = melt(ice);// 녹이기
			boolean[][] v = new boolean[N][M];// 방문 체크
			int pieces = 0;// 빙산 조각 수
			for (int j = 1; j < N - 1; j++) {
				for (int k = 1; k < M - 1; k++) {
					if (ice[j][k] == 0 || v[j][k]) {// 빙산이 없거나 이미 확인한 빙산인 경우
						continue;// 넘기기
					}
					bfs(ice, v, new Data(k, j));// 빙산 덩어리 확인
					pieces += 1;// 조각 수 +1
				}
			}
			if (pieces >= 2) {// 빙산 조각이 2조각 이상인 경우
				ans = time;// 걸린 시간 저장
				break;// 녹이기 중지
			}
		}
		System.out.println(ans);// 녹이는데 걸리는 시간 또는 0 출력
	}

	private static void bfs(int[][] ice, boolean[][] v, Data d) {// 덩어리 확인
		Queue<Data> q = new LinkedList<>();// 확인할 칸을 저장할 큐
		q.offer(d);// 한 조각 넣기
		while (!q.isEmpty()) {
			Data cur = q.poll();// 한 조각 꺼내기
			for (int i = 0; i < 4; i++) {// 사방으로 살펴보기
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (ice[ny][nx] == 0 || v[ny][nx]) {// 빙하가 없거나 확인한 곳이면
					continue;// 지나가기
				}
				v[ny][nx] = true;// 방문 체크
				q.offer(new Data(nx, ny));// 조각 넣기
			}
		}
	}

	private static int[][] melt(int[][] ice) {// 녹이기
		int[][] map = new int[N][M];// 주변에 바닷물이 있는 칸 확인할 배열
		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < M - 1; j++) {
				if (ice[i][j] == 0) {// 빙하가 없으면
					continue;// 지나가기
				}
				for (int k = 0; k < 4; k++) {// 주변 4칸 살펴보기
					int nx = j + dx[k];
					int ny = i + dy[k];
					if (ice[ny][nx] != 0) {// 빙하가 있으면
						continue;// 지나가기
					}
					map[i][j] -= 1;// 바닷물 체크
				}
			}
		}
		sum = 0;// 빙하 다 녹았는지 확인
		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < M - 1; j++) {
				map[i][j] += ice[i][j];// 빙하 녹이기
				map[i][j] = Math.max(map[i][j], 0);// 높이는 0보다 더 줄어들지 X
				sum += map[i][j];// 빙하 높이 더하기
			}
		}
		return map;// 빙하 높이 정보 반환
	}

	static class Data {// 빙하 칸 좌표 정보
		int x, y;

		public Data(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}