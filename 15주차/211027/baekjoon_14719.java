package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_14719 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		boolean[][] map = new boolean[H][W];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<W; i++) {
			int k = Integer.parseInt(st.nextToken());
			for(int j = 0; j<k; j++) {
				map[H-1-j][i] = true;
			}
		}
		int ans = 0;
		for(int j = 0; j<H; j++) {
			int cnt = 0;
			boolean flag = false;
			for(int i = 0; i<W; i++) {
				// 처음으로 채워진 칸 만나면
				if(flag == false && map[j][i] == true) {
					flag = true;
					cnt = 0;
					continue;
				}
				// 채워진 칸 만난 후에 빈칸에는 물이 고인다
				if(flag == true && map[j][i] == false) {
					cnt++;
					continue;
				}
				
				// 채워진 칸을 또 만난다면 고인물 업데이트
				if(flag == true && map[j][i] == true) {
					ans+=cnt;
					cnt = 0;
					continue;
				}
				
			}
		}
		System.out.println(ans);
	}
}
