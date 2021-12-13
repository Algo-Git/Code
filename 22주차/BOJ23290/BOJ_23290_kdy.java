import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class N23290 {// BOJ 23290. 마법사 상어와 복제

	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int fish;
	static int[] sy = { 0, -1, 0, 1 };
	static int[] sx = { -1, 0, 1, 0 };
	static Shark shark;
	static int[][] smell;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[][] map = new ArrayList[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			map[x][y].add(d);
		}
		shark = new Shark(0, 0);
		st = new StringTokenizer(br.readLine());
		shark.x = Integer.parseInt(st.nextToken());
		shark.y = Integer.parseInt(st.nextToken());

		fish = M;
		smell = new int[5][5];
		for (int i = 1; i <= S; i++) {
			smell[0][0] = i;
			map = practice(map);
		}
		System.out.println(fish);
	}

	private static void reduceSmell() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (smell[i][j] == 0) {
					continue;
				}
				if (smell[0][0] - smell[i][j] == 2) {
					smell[i][j] = 0;
				}
			}
		}
	}

	private static ArrayList<Integer>[][] practice(ArrayList<Integer>[][] map) {
		ArrayList<Integer>[][] cmap = copy(map);
		cmap = moveFish(cmap);
		int[] dir = find(cmap);
		cmap = moveShark(cmap, dir);
		reduceSmell();
		cmap = copymagic(cmap, map);
		return cmap;
	}

	private static ArrayList<Integer>[][] copymagic(ArrayList<Integer>[][] cmap, ArrayList<Integer>[][] map) {
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				for (int k = 0; k < map[i][j].size(); k++) {
					cmap[i][j].add(map[i][j].get(k));
					fish++;
				}
			}
		}
		return cmap;
	}

	private static int[] find(ArrayList<Integer>[][] cmap) {
		int[] dir = new int[3];
		int max = -1;
		for (int i = 0; i < 4; i++) {
			loop1: for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					boolean[][] v = new boolean[5][5];
					int cnt = 0;
					int nx = shark.x + sx[i];
					int ny = shark.y + sy[i];
					if (nx < 1 || ny < 1 || nx > 4 || ny > 4) {
						break loop1;
					}
					cnt += cmap[nx][ny].size();
					v[nx][ny] = true;
					nx += sx[j];
					ny += sy[j];
					if (nx < 1 || ny < 1 || nx > 4 || ny > 4) {
						break;
					}
					if (!v[nx][ny]) {
						cnt += cmap[nx][ny].size();
					}
					v[nx][ny] = true;
					nx += sx[k];
					ny += sy[k];
					if (nx < 1 || ny < 1 || nx > 4 || ny > 4) {
						continue;
					}
					if (!v[nx][ny]) {
						cnt += cmap[nx][ny].size();
					}
					if (max < cnt) {
						max = cnt;
						dir[0] = i;
						dir[1] = j;
						dir[2] = k;
					}
				}
			}
		}
		return dir;
	}

	private static ArrayList<Integer>[][] moveShark(ArrayList<Integer>[][] cmap, int[] dir) {
		for (int i = 0; i < 3; i++) {
			shark.x += sx[dir[i]];
			shark.y += sy[dir[i]];
			if (cmap[shark.x][shark.y].size() != 0) {
				fish -= cmap[shark.x][shark.y].size();
				smell[shark.x][shark.y] = smell[0][0];
				cmap[shark.x][shark.y].clear();
			}
		}
		return cmap;
	}

	private static ArrayList<Integer>[][] moveFish(ArrayList<Integer>[][] cmap) {
		ArrayList<Integer>[][] after = new ArrayList[5][5];
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				after[i][j] = new ArrayList<>();
			}
		}
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				for (int dir : cmap[i][j]) {
					boolean flag = false;
					for (int d = 8; d > 0; d--) {
						int nx = i + dx[(dir + d) % 8];
						int ny = j + dy[(dir + d) % 8];
						if (nx < 1 || ny < 1 || nx > 4 || ny > 4) {
							continue;
						}
						if (nx == shark.x && ny == shark.y) {
							continue;
						}
						if (smell[nx][ny] != 0) {
							continue;
						}
						after[nx][ny].add((dir + d) % 8);
						flag = true;
						break;
					}
					if (!flag) {
						after[i][j].add(dir);
					}
				}
			}
		}
		return after;
	}

	private static ArrayList<Integer>[][] copy(ArrayList<Integer>[][] map) {
		ArrayList<Integer>[][] cmap = new ArrayList[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				cmap[i][j] = new ArrayList<>();
				cmap[i][j] = map[i][j];
			}
		}
		return cmap;
	}

	static class Shark {
		int x, y;

		public Shark(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

}
