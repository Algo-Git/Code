package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [골드2] 청소년 상어
// 빡빡한 구현.. & dfs
// 자리바꾸기(시뮬레이션) 진짜헷갈림
// 주의할 점은 dfs호출할 때마다 새로 map을 만들어줘야한다
public class baekjoon_19236_dfs {
	static int[] dx = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[][] map = new int[4][4]; // 상어는 -1, 빈자리는 0
		int[][] dir = new int[4][4];
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dir[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		max = map[0][0];
		int sharkD = dir[0][0];
		
		// 상어가 있는 곳은 -1로 해 주었다.
		map[0][0] = -1;
		dir[0][0] = -1;
		
		dfs(0,0,sharkD,max, map, dir);
		
		System.out.println(max);
	}
	
	private static void dfs(int x, int y, int d, int cnt, int[][] mapbefore, int[][] dirbefore) {
		// 물고기의 위치를 바꾼다.
		Info info = change(mapbefore, dirbefore);
		int[][] map = info.map;
		int[][] dir = info.dir;
		
		int step = 0;
		// 기저조건: 더이상 갈 곳이 없다면
		while(true) {
			step++;
			int nx = x+step*dx[d];
			int ny = y+step*dy[d];
			
			// 기저조건
			if(nx<0 || nx>=4 || ny<0 || ny>=4) {
				max = Math.max(max, cnt);
				return;
			}
				
			// 빈곳이라면 다음칸 확인해라
			if(map[nx][ny] == 0) {
				continue;
			}
			
			// 물고기가 있다면?
			int nk = map[nx][ny]; // 상어가 먹을 물고기 크기
			int nd = dir[nx][ny]; // 상어가 먹을 물고기 방향
			
			int[][] mapNext = new int[4][4];
			int[][] dirNext = new int[4][4];
			for(int i = 0; i<4; i++) {
				mapNext[i] = map[i].clone();
				dirNext[i] = dir[i].clone();
			}
			mapNext[x][y] = 0;
			mapNext[nx][ny] = -1;
			
			dirNext[x][y] = 0;
			dirNext[nx][ny] = -1;
			
			dfs(nx,ny,nd,cnt+nk,mapNext,dirNext);
		}
		
	}

//	private static void printmap(int[][] map) {
//		for(int i = 0; i<4; i++) {
//			for(int j = 0; j<4; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//	}
	
	private static Info change(int[][] map1, int[][] dir1) {
		int[] numX = new int[17];
		int[] numY = new int[17];

		int[][] map = new int[4][4];
		int[][] dir = new int[4][4];
		for(int i = 0; i<4; i++) {
			map[i] = map1[i].clone();
			dir[i] = dir1[i].clone();
		}
		boolean[] exist = new boolean[17];
		for(int i = 0; i<4; i++) {
			for(int j = 0; j<4; j++) {
				int num = map[i][j];
				// 상어라면, 혹은 빈자리라면
				if(num == -1 || num == 0) {
					continue;
				}
				numX[num] = i;
				numY[num] = j;
				exist[num] = true;
			}
		}
		
		for(int num = 1; num<=16; num++) {
			// 숫자가 이미 먹히고 없으면 continue;
			if(!exist[num]) continue;
			
			// 숫자가 있으면? 방향을 확인하고 바꿈
			int x = numX[num];
			int y = numY[num];
			// 내가 지금 보고 있는 숫자 물고기의 방향
			int d = dir[x][y];
			while (true) {
				int nx = x + dx[d];
				int ny = y + dy[d];

				/// 범위 벗어나거나 상어있는 자리면 반시계방향으로 돌려라.
				if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || map[nx][ny] == -1) {
					d++;
					if (d == 9) {
						d = 1;
					}
					continue;
				}

				// 가고싶은 곳이 비어있는 자리면
				if (map[nx][ny] == 0) {
					map[nx][ny] = num;
					dir[nx][ny] = d;
					
					map[x][y] = 0;
					dir[x][y] = 0;
					
					numX[num] = nx;
					numY[num] = ny;
					break;
				}
				
				// 가고싶은 곳에 물고기 있다면 자리 바꾼다.
				int nextnum = map[nx][ny];
				map[x][y] = nextnum;
				dir[x][y] = dir[nx][ny];
				
				map[nx][ny] = num;
				dir[nx][ny] = d;
				
				numX[nextnum] = x;
				numY[nextnum] = y;
				numX[num] = nx;
				numY[num] = ny;
				break;
			}
		}
		
		return new Info(map, dir);
	}

	private static class Info {
		int[][] map;
		int[][] dir;

		public Info(int[][] map, int[][] dir) {
			this.map = map;
			this.dir = dir;
		}
	}
}
