import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15685_kyj {
	// [골드4] 드래곤 커브
	// 시계방향으로 90도 돌린다 => 원본의 x좌표 -> turn의 y좌표, 원본의 y 좌표 -> -붙이면  turn의 y좌표
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[][] visited = new boolean[101][101];
		
		for(int n = 0; n<N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); // 시작 x좌표
			int y = Integer.parseInt(st.nextToken()); // 시작 y좌표
			int d = Integer.parseInt(st.nextToken()); // 시작 방향
			int g = Integer.parseInt(st.nextToken()); // 세대
			
			List<Data> res = dragon(d, g);
			for(int i = 0; i<res.size(); i++) {
				Data data = res.get(i);
				visited[data.x+x][data.y+y] = true;
			}
		}
		int ans = 0;
		for(int i = 0; i<100; i++) {
			for(int j = 0; j<100; j++) {
				if(visited[i][j] && visited[i+1][j] && visited[i][j+1] && visited[i+1][j+1]) {
					ans++;
				}
			}
		}
		System.out.println(ans);
	}

	private static List<Data> dragon(int d, int g) {
		List<Data> origin = new ArrayList<>();
		List<Data> turn = new ArrayList<>();

		// 시작
		origin.add(new Data(0,0));
		turn.add(new Data(0,0));

		switch (d) {
		case 0: // x좌표 증가 ->
			origin.add(new Data(1, 0));
			turn.add(new Data(0,1));
			break;
		case 1: // y좌표 감소 ^
			origin.add(new Data(0, -1));
			turn.add(new Data(1,0));
			break;
		case 2: // x좌표 감소 <-
			origin.add(new Data(-1, 0));
			turn.add(new Data(0,-1));
			break;
		case 3: // y좌표 증가 
			origin.add(new Data(0, 1));
			turn.add(new Data(-1,0));
			break;
		default:
			break;
		}
		
		for(int i = 1; i<=g; i++) { // 끝과 끝을 이어야 한다.
			// 얼만큼 평행이동 해야 하는가? turn의 끝좌표 - origin의 끝좌표
			int n = turn.size();
			Data lastTurn = turn.get(turn.size()-1);
			Data lastOrigin = origin.get(origin.size()-1);
			
			int mx = lastOrigin.x-lastTurn.x;
			int my = lastOrigin.y-lastTurn.y;
			
			for(int j = n-2; j>=0; j--) {
				origin.add(new Data(turn.get(j).x + mx, turn.get(j).y+ my));
				turn.add(new Data(-(turn.get(j).y+ my),turn.get(j).x + mx));
			}
//			System.out.println(i + "세대 origin: " + origin.toString());
//			System.out.println(i + "세대 turn: " + turn.toString());
		}

		return origin;
	}

	private static class Data {
		int x;
		int y;

		public Data(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Data [x=" + x + ", y=" + y + "]";
		}
	}
}
