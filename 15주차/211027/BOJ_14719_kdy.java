import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14719_kdy {// BOJ 14719 빗물

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());// 세로 길이
		int W = Integer.parseInt(st.nextToken());// 가로 길이
		int sum = 0;// 고인 빗물 총량
		st = new StringTokenizer(br.readLine());
		boolean[][] map = new boolean[H][W];// 2차원 세계
		for (int i = 0; i < W; i++) {// 블록 입력받기
			int h = Integer.parseInt(st.nextToken());
			for (int j = 0; j < h; j++) {// 블록 쌓기
				map[j][i] = true;
			}
		}
		for (int i = 0; i < H; i++) {// 바닥부터 살펴보기
			boolean flag = false;// 채워진 칸 못 만남
			int cnt = 0;// 고인 빗물
			for (int j = 0; j < W; j++) {// 왼쪽부터 살펴보기
				if (!flag && map[i][j]) {// 처음 채워진 칸 만나면
					flag = true;// 채워진 칸 만났다고 표시
				} else if (flag && !map[i][j]) {// 채워진 칸 이후에 빈 칸 나오면
					cnt++;// 갯수 세어보기
				} else if (flag && map[i][j]) {// 다시 채워진 칸을 만나면
					sum += cnt;// 빈 칸 수만큼 물이 고임
					cnt = 0;// 빈 칸 수 0으로 초기화
				}
			}
		}
		System.out.println(sum);// 고인 빗물의 총량 출력
	}

}
