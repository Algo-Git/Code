package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swExpert_5650 {
	static int N;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 }; // 상0하1좌2우3
	static int[] dy = { 0, 0, -1, 1 };
	// 방향 별로 숫자 만났을 때 1블록,2블록,3블록,5블록 어떤 방향으로 바뀌는지
	static int[][] d_n = { { -1, 1, 3, 2, 1, 1 }, { -1, 3, 0, 0, 2, 0 }, { -1, 0, 1, 3, 3, 3 }, { -1, 2, 2, 1, 0, 2 } };
	// 방향 별로 반사되는 블럭 번호
	static int[][] reflect = { { 1, 4, 5 }, { 2, 3, 5 }, { 3, 4, 5 }, { 1, 2, 5 } };
	static int Max;
	static int[] warmX1, warmY1, warmX2, warmY2;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			warmX1 = new int[11];
			warmY1 = new int[11];
			warmX2 = new int[11];
			warmY2 = new int[11];

			for (int i = 6; i < 11; i++) {
				warmX1[i] = -1;
				warmY1[i] = -1;
				warmX2[i] = -1;
				warmY2[i] = -1;
			}
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int k = Integer.parseInt(st.nextToken());
					map[i][j] = k;

					// 웜홀의 위치 저장
					if (k >= 6) {
						if (warmX1[k] == -1) {
							warmX1[k] = i;
							warmY1[k] = j;
						} else {
							warmX2[k] = i;
							warmY2[k] = j;
						}
					}
				}
			}
//			
//			System.out.println(Arrays.toString(warmX1));
//			System.out.println(Arrays.toString(warmY1));
//			System.out.println(Arrays.toString(warmX2));
//			System.out.println(Arrays.toString(warmY2));
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
			Max = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != 0) {
						continue;
					}
					for (int d = 0; d < 4; d++) {
//						int nx = i+dx[d];
//						int ny = j+dy[d];
						
//						if(nx<0 || nx>=N || ny<0|| ny>=N) {
//							continue;
//						}
						
//						if(map[nx][ny] != 0) {
							go(i, j, d);
//						}
					}
				}
			}

			System.out.printf("#%d %d%n", test_case, Max);
		}
	}

	private static void go(int startX, int startY, int d) {
//		System.out.printf("check, startX: %d , startY: %d, d: %d%n", startX, startY, d);
		int cnt = 0;
		int curX = startX;
		int curY = startY;
		outer: while (true) {
			int nx = curX + dx[d];
			int ny = curY + dy[d];
//			System.out.printf("d: %d, curX: %d, curY: %d, nx: %d, ny: %d %n",d,curX,curY,nx,ny);
			// 시작 위치로 돌아 온다면 
			if (nx == startX && ny == startY) {
				Max = Math.max(cnt, Max);
				break;
			}
			
			// 맵을 벗어난다면(벽만났을때)
			if ((nx < 0) || (nx > N - 1) || (ny < 0) || (ny > N - 1)) {
				Max = Math.max(Max, cnt * 2+1);
				break;
			}

			int num = map[nx][ny];
			// 블랙홀이면
			if (num == -1) {
				Max = Math.max(cnt, Max);
				break;
			}

			// 반사되는 블럭 만났을 때(벽과 같은 효과)
			for (int i = 0; i < 3; i++) {
				if (reflect[d][i] == num) {
					Max = Math.max(Max, cnt * 2 + 1);
					break outer;
				}
			}

			// 웜홀이면
			if (num >= 6 && num <= 10) {
				// 첫번째 웜홀이라면
				if (warmX1[num] == nx && warmY1[num] == ny) {
					// 위치만 짝꿍인 웜홀로 옮긴다.
					curX = warmX2[num];
					curY = warmY2[num];
				} else {
					curX = warmX1[num];
					curY = warmY1[num];
				}
				continue;
			}

			// 방향만 바뀌는 블록이라면
			if (num >= 1 && num <= 5) {
				cnt++;
				curX = nx;
				curY = ny;
				d = d_n[d][num];
				continue;
			}
			
			if (num == 0) {// 아무 것도 없으면
				curX = nx;
				curY = ny;
				continue;
			}
		}
	}
}
