package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[골드5] 테트로 미노
public class BOJ_14500_kyj {// ㅡ ㅁ ㄴ N ㅜ =>2,1,8,4,4
	static int[][] blockX = { { 0, 0, 0, 0 }, { 0, 1, 0, 1 }, { 0, 1, 2, 2 }, { 0, 1, 1, 2 }, { 0, 0, 1, 0 } };
	static int[][] blockY = { { 0, 1, 2, 3 }, { 0, 0, 1, 1 }, { 0, 0, 0, 1 }, { 0, 0, 1, 1 }, { 0, 1, 1, 2 } };
	static int[] maxX = { 0, 1, 2, 2, 1 };
	static int[] maxY = { 3, 1, 1, 1, 2 };

	static int N, M, Max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map1 = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map1[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < 5; i++) {
			count(map1, i);
		}
		
		int[][] mapReverse1 = reverse(map1);
		for (int i : new int[]{2,3,4}) {
			count(mapReverse1, i);
		}
		
		int[][] map2 = turnRight(map1);
		int[][] mapReverse2 = reverse(map2);
		
		for (int i : new int[]{0,2,3,4}) {
			count(map2, i);
		}
		
		for (int i : new int[]{2,3,4}) {
			count(mapReverse2, i);
		}
		
		int[][] map3 = turnRight(map2);
		int[][] mapReverse3 = reverse(map3);

		int[][] map4 = turnRight(map3);
		int[][] mapReverse4 = reverse(map4);

		count(map3, 2);
		count(mapReverse3, 2);
		count(map4, 2);
		count(mapReverse4, 2);
		
		System.out.println(Max);
	}

	private static int[][] turnRight(int[][] map) {
		int m = map[0].length;
		int n = map.length;
		int[][] map2 = new int[m][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map2[m - 1 - j][i] = map[i][j];
			}
		}
		return map2;
	}

	private static int[][] reverse(int[][] map) { // 위아래 뒤집기
		int n = map.length;
		int m = map[0].length;
		int[][] map2 = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map2[n - 1 - i][j] = map[i][j];
			}
		}
		return map2;
	}

	private static void count(int[][] map, int num) {
		int n = map.length;
		int m = map[0].length;
		for (int i = 0; i < n - maxX[num]; i++) {
			for (int j = 0; j < m - maxY[num]; j++) {
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
//					System.out.printf("num: %d, i: %d, j: %d, d: %d, nx: %d, ny: %d%n", num,i,j,d,i + blockX[num][d],j + blockY[num][d]);
					cnt += map[i + blockX[num][d]][j + blockY[num][d]];
				}
				Max = Math.max(cnt, Max);
			}
		}
	}

	private static void printMap(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
