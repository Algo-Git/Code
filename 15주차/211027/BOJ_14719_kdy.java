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



/*
* 1. 이전 칸 중 블럭이 최대 높이인 칸의 위치와 높이를 저장
* 그 칸 바로 옆부터 오른쪽으로 움직이면서
* 칸이 높아질때마다 그 전칸까지 빗물 고이게하는 방식
* => 최댓값 오른쪽에서 작은 값끼리 고이면 더해지지 않음
* 2. 왼쪽값 중에 값이 감소하기 시작하는 지점 선택
* 오른쪽 값 중 가장 최댓값 고르기
* 사이 값들에 대해 오른쪽과 왼쪽 값 중에 작은 값과의 차이만큼 고인 빗물로 더해주기
* 오른쪽값 위치로 왼쪽 값을 이동시키고 그 오른쪽 중 최댓값 찾고 계속 반복
* => 반례는 못 찾았지만 바로 틀림
* 3. 2차원 배열 만들어서 가로줄 한줄씩 보고 양 옆으로 채워진 칸 있으면 고인 물 추가하기 => 성공
*/
