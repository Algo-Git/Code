import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N1238 {// BOJ 1238. 파티

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 마을 개수
		int M = Integer.parseInt(st.nextToken());// 도로 수
		int X = Integer.parseInt(st.nextToken()) - 1;// 파티가 열리는 마을

		int[][] map = new int[N][N];// 마을간 연결 정보를 가지는 지도
		for (int i = 0; i < N; i++) {// 모든 연결이 X인 상태로 초기화
			Arrays.fill(map[i], 1000000);
		}
		for (int i = 0; i < M; i++) {// 도로 정보 입력받기
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;// 출발지
			int to = Integer.parseInt(st.nextToken()) - 1;// 도착지
			int T = Integer.parseInt(st.nextToken());// 소요 시간
			map[from][to] = T;// 출발지에서 도착지로 이동 시 T시간 소요된다고 지도에 표시
		}

		int max = 0;// 집->X마을->집 최대 소요 시간
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);// i->k->j일 때 i->j와 비교해서 최단거리로 수정하기
				}
			}
		}
		for (int i = 0; i < N; i++) {// 집->X마을->집 최대 소요 시간 구하기
			max = Math.max(map[i][X] + map[X][i], max);
		}
		System.out.println(max);// 집->X마을->집 최대 소요 시간 출력
	}

}
