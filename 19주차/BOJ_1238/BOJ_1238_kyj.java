import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [골드3] 파티
// 플로이드-와샬/2번 다익스트라 생각해봄
// 처음에는 플로이드-와샬로 풀었다. 통과했지만 시간이 2392ms => O(n^3)
public class BOJ_1238_kyj {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		// 인접 매트리스
		int[][] map = new int[N+1][N+1];
		
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				map[i][j] = 1000000; // 큰 수로 초기화 (이 부분 실수)
			}
		}
		
		// 맵 채우기
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			map[start][end] = time;
		}		
		
		// X마을에 사는 학생
		map[X][X] = 0;
		
		// 플로이드 와샬(경,출,도)
		for(int k = 1; k<=N; k++) {
			for(int i = 1; i<=N; i++) {
				for(int j = 1; j<=N; j++) {
					if(map[i][k] + map[k][j] < map[i][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}

		int max = 0;
		for(int i = 1; i<=N; i++) {
			if(max<map[i][X]+map[X][i]) {
				max = map[i][X]+map[X][i];
			}
		}
		System.out.println(max);
	}
}
