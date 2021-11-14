package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [골드 4] 개리맨더링 2
// 구현...
public class BOJ_17779_kyj {
	static int[][] map;
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N= Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int min = Integer.MAX_VALUE;
		// 기준점 x,y 와 경계선 길이 d1, d2 정하기
		for(int x=1; x<=N; x++) {
			for(int y=1; y<=N; y++) {
				
				for(int d1 = 1; d1<=N-1; d1++) {
					for(int d2 = 1; d2<=N-1; d2++) {
						if(x+d1+d2>N || 1>y-d1 || y+d2>N) {
							continue;
						}
//						System.out.printf("x:%d, y:%d, d1:%d, d2:%d%n",x,y,d1,d2);
						int res = calc(x,y,d1,d2);
//						System.out.println("res: "+ res);
						if(min>res) {
							min = res;
						}
					}
				}
			}
		}
		System.out.println(min);
	}

	private static int calc(int x, int y, int d1, int d2) {
		boolean[][] visited = new boolean[N+1][N+1];
		
		// 경계선 부분은 loc5에 더하고 visited 처리
		int loc5 = 0;
		for(int i = 0; i<=d1; i++) {
			if(visited[x+i][y-i]) {
				continue;
			}
			visited[x+i][y-i] = true;
			loc5 += map[x+i][y-i];
		}
		
		for(int i = 0; i<=d2; i++) {
			if(visited[x+i][y+i]) {
				continue;
			}
			visited[x+i][y+i] = true;
			loc5 += map[x+i][y+i];
		}
		
		for(int i = 0; i<=d2; i++) {
			if(visited[x+d1+i][y-d1+i]) {
				continue;
			}
			visited[x+d1+i][y-d1+i] = true;
			loc5 += map[x+d1+i][y-d1+i];
		}
		
		for(int i = 0; i<=d1; i++) {
			if(visited[x+d2+i][y+d2-i]) {
				continue;
			}
			visited[x+d2+i][y+d2-i] = true;
			loc5 += map[x+d2+i][y+d2-i];
		}
		
		// loc1 구하기
		int loc1 = 0;
		for(int i = 1; i<x+d1; i++) {
			for(int j = 1; j<=y; j++) {
				if(visited[i][j]) {
					break;
				}
				loc1+=map[i][j];
				visited[i][j] = true;
			}
		}
		
		// loc2 구하기
		int loc2 = 0;
		for(int i = 1; i<=x+d2; i++) {
			for(int j = N; j>y; j--) {
				if(visited[i][j]) {
					break;
				}
				loc2+=map[i][j];
				visited[i][j] = true;
			}
		}
		
		// loc3 구하기
		int loc3 = 0;
		for(int i = x+d1; i<=N; i++) {
			for(int j = 1; j<y-d1+d2; j++) {
				if(visited[i][j]) {
					break;
				}
				loc3+=map[i][j];
				visited[i][j] = true;
			}
		}
		
		// loc4 구하기
		int loc4 = 0;
		for(int i = x+d2+1; i<=N; i++) {
			for(int j = N; j>=y-d1+d2; j--) {
				if(visited[i][j]) {
					break;
				}
				loc4+=map[i][j];
				visited[i][j] = true;
			}
		}
		
		// 경계선에 포함되어 있지 않은 것들을 loc5에 더해 줌
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				if(visited[i][j]) {
					continue;
				}
				loc5+= map[i][j];
				visited[i][j] = true;
			}
		}
		
		int M = Math.max(Math.max(Math.max(Math.max(loc1, loc2),loc3),loc4),loc5);
		int m = Math.min(Math.min(Math.min(Math.min(loc1, loc2),loc3),loc4),loc5);
//		System.out.printf("loc1: %d, loc2: %d, loc3: %d, loc4: %d, loc5: %d%n",loc1,loc2,loc3,loc4,loc5);
		return M-m;
	}
}
