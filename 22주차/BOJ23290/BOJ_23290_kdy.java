import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_23290_kdy {// BOJ 23290. 마법사 상어와 복제

	// 물고기 이동 방향
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int fish;
	// 상어 이동 방향(상좌하우)
	static int[] sy = { 0, -1, 0, 1 };
	static int[] sx = { -1, 0, 1, 0 };
	static Shark shark;
	static int[][] smell;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());// 물고기 수
		int S = Integer.parseInt(st.nextToken());// 연습 횟수
		ArrayList<Integer>[][] map = new ArrayList[5][5];// 4X4 격자
		for (int i = 0; i < 5; i++) {// 격자 초기화
			for (int j = 0; j < 5; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		for (int i = 0; i < M; i++) {// 물고기 정보 입력받기
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());// 물고기 x좌표
			int y = Integer.parseInt(st.nextToken());// 물고기 y좌표
			int d = Integer.parseInt(st.nextToken()) - 1;// 물고기 방향
			map[x][y].add(d);// 격자에 물고기 추가
		}
		shark = new Shark(0, 0);// 상어
		st = new StringTokenizer(br.readLine());
		shark.x = Integer.parseInt(st.nextToken());// 상어 x좌표
		shark.y = Integer.parseInt(st.nextToken());// 상어 y좌표

		fish = M;// 물고기 수 초기화 => M마리
		smell = new int[5][5];// 냄새 배열
		for (int i = 1; i <= S; i++) {// 연습 횟수만큼 연습하기
			smell[0][0] = i;// 냄새배열 0, 0번째에 연습회차 저장
			map = practice(map);// 연습해보기
		}
		System.out.println(fish);// 연습을 모두 마친 후 격자에 있는 물고기 수
	}

	// 연습
	private static ArrayList<Integer>[][] practice(ArrayList<Integer>[][] map) {
		// 물고기 복제 마법
		ArrayList<Integer>[][] cmap = copy(map);

		// 모든 물고기 한 칸 이동
		cmap = moveFish(cmap);

		// 상어가 이동할 방향 정하기
		int[] dir = find(cmap);

		// 상어 3칸 이동
		cmap = moveShark(cmap, dir);

		// 두 번 전 연습에서 생긴 물고기 냄새 지우기
		removeSmell();

		// 복제 마법 완료
		cmap = copymagic(cmap, map);

		return cmap;
	}

	// 모든 물고기 한 칸 이동
	private static ArrayList<Integer>[][] moveFish(ArrayList<Integer>[][] cmap) {
		ArrayList<Integer>[][] after = new ArrayList[5][5];// 이동한 물고기 정보를 저장할 격자
		for (int i = 1; i < 5; i++) {// 격자 초기화
			for (int j = 1; j < 5; j++) {
				after[i][j] = new ArrayList<>();
			}
		}
		// 모든 격자를 살펴보며 물고기 이동시키기
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				for (int dir : cmap[i][j]) {// 격자 한 칸에 있는 물고기 순서대로 이동
					boolean flag = false;// 이동할 수 있는 칸이 있는지 체크
					for (int d = 8; d > 0; d--) {// 이동 방향이 이동할 수 있는 칸을 향할 때까지 반시계방향으로 회전
						int nx = i + dx[(dir + d) % 8];// x좌표 이동
						int ny = j + dy[(dir + d) % 8];// y좌표 이동
						if (nx < 1 || ny < 1 || nx > 4 || ny > 4) {// 격자 범위를 벗어나는 경우
							continue;
						}
						if (nx == shark.x && ny == shark.y) {// 상어가 존재하는 경우
							continue;
						}
						if (smell[nx][ny] != 0) {// 물고기 냄새가 있는 칸인 경우
							continue;
						}
						after[nx][ny].add((dir + d) % 8);// 이동한 위치에 물고기 추가
						flag = true;// 물고기가 이동함
						break;// 종료
					}
					if (!flag) {// 이동할 수 있는 칸이 없는 경우
						after[i][j].add(dir);// 제자리에 있기
					}
				}
			}
		}
		return after;// 물고기 이동 정보를 가진 격자 반환
	}

	// 격자 복제
	private static ArrayList<Integer>[][] copy(ArrayList<Integer>[][] map) {
		ArrayList<Integer>[][] cmap = new ArrayList[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				cmap[i][j] = map[i][j];// 얕은 복사는 값이 함께 바뀜ㅠㅠ
			}
		}
		return cmap;
	}

	// 상어가 이동할 방향 정하기 => [상, 좌, 하, 우] 순으로 우선순위 가짐
	private static int[] find(ArrayList<Integer>[][] cmap) {
		int[] dir = new int[3];// 상어가 이동할 3칸의 방향 저장
		int max = -1;// 상어가 이동했을 때 제외시킬 수 있는 물고기의 최댓값 => 0일 수 있기때문에 -1로 초기화
		for (int i = 0; i < 4; i++) {// 첫번째 이동할 칸 방향 정하기
			loop1: for (int j = 0; j < 4; j++) {// 두번째 이동할 칸 방향
				for (int k = 0; k < 4; k++) {// 세번째 이동할 칸 방향 정하기
					int cnt = 0;// 제외한 물고기 수
					// 첫번째 칸으로 이동
					int nx = shark.x + sx[i];// 상어의 x좌표 이동
					int ny = shark.y + sy[i];// 상어의 y좌표 이동
					if (nx < 1 || ny < 1 || nx > 4 || ny > 4) {// 격자 범위를 벗어나는 경우
						break loop1;// 첫번째 이동 방향 바꾸기
					}
					cnt += cmap[nx][ny].size();// 이동한 칸에 존재하는 물고기 수만큼 제외
					int fx = nx;
					int fy = ny;

					// 두번째 칸으로 이동
					nx += sx[j];// 상어의 x좌표 이동
					ny += sy[j];// 상어의 y좌표 이동
					if (nx < 1 || ny < 1 || nx > 4 || ny > 4) {// 격자를 벗어나는 경우
						break;// 두번째 이동 방향 바꾸기
					}
					cnt += cmap[nx][ny].size();// 칸에 존재하는 물고기 수만큼 제외

					// 3번째 칸으로 이동
					nx += sx[k];// 상어의 x좌표 이동
					ny += sy[k];// 상어의 y좌표 이동
					if (nx < 1 || ny < 1 || nx > 4 || ny > 4) {// 격자를 벗어나는 경우
						continue;// 세번째 이동 방향 바꾸기
					}
					if (fx != nx || fy != ny) {// 방문한 칸이 아닌 경우
						cnt += cmap[nx][ny].size();// 칸에 존재하는 물고기 수만큼 제외
					}
					if (max < cnt) {// 현재 이동 방향에서 제외할 수 있는 물고기의 수가 최대인 경우(최댓값과 같은 경우는 사전 순으로 앞서지않기 때문에 고려X)
						max = cnt;// 최대로 제외할 수 있는 물고기 수 변경
						dir[0] = i;// 첫번째 이동 방향
						dir[1] = j;// 두번째 이동 방향
						dir[2] = k;// 세번째 이동 방향
					}
				}
			}
		}
		return dir;// 이동 방향 배열 반환
	}

	// 상어 3칸 이동
	private static ArrayList<Integer>[][] moveShark(ArrayList<Integer>[][] cmap, int[] dir) {
		for (int i = 0; i < 3; i++) {// 3칸 이동하기
			shark.x += sx[dir[i]];// 상어의 x좌표 이동
			shark.y += sy[dir[i]];// 상어의 y좌표 이동
			if (cmap[shark.x][shark.y].size() != 0) {// 상어가 이동한 칸에 물고기가 존재하는 경우
				fish -= cmap[shark.x][shark.y].size();// 물고기 제외 => 현재 물고기 수 감소
				smell[shark.x][shark.y] = smell[0][0];// 냄새 남기기 => 연습 횟수로 표시
				cmap[shark.x][shark.y].clear();// 칸의 물고기 제거
			}
		}
		return cmap;// 상어가 지나간 칸의 물고기를 제외한 격자 반환
	}

	// 두 번 전 연습에서 생긴 물고기 냄새 지우기
	private static void removeSmell() {
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				if (smell[i][j] == 0) {// 물고기 냄새가 없는 경우
					continue;// 지나가기
				}
				if (smell[0][0] - smell[i][j] == 2) {// 두 번 전 연습에서 생긴 물고기 냄새
					smell[i][j] = 0;// 제거
				}
			}
		}
	}

	// 복제 마법 완료
	private static ArrayList<Integer>[][] copymagic(ArrayList<Integer>[][] cmap, ArrayList<Integer>[][] map) {
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				// 칸에 있는 모든 물고기 복사
				for (int k = 0; k < map[i][j].size(); k++) {
					cmap[i][j].add(map[i][j].get(k));
					fish++;// 격자에 있는 물고기 수 +1
				}
			}
		}
		return cmap;// 복제 마법을 완료한 격자 반환
	}

	static class Shark {// 상어
		// 상어의 x, y좌표
		int x, y;

		public Shark(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

}
