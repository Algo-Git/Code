import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// [실버3] 계단오르기
// DP
public class BOJ_2579_yejinKim {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 계단 개수는 1개 ~ 300개
		int[][] stairs = new int[N+1][2]; // 0은 이전 것을 밟은 경우, 1은 이전 것을 안밟은 경우
		
		stairs[1][0] = Integer.parseInt(br.readLine());
		stairs[1][1] = stairs[1][0];
		
		for(int i = 2; i<=N; i++) {
			int stair = Integer.parseInt(br.readLine());
			stairs[i][0] = stair + stairs[i-1][1]; // 전전것은 안밟아야함
			stairs[i][1] = stair + Math.max(stairs[i-2][0],stairs[i-2][1]); //이전것을 안밟으면 전전은 밟든 말든
		}
		
		System.out.println(Math.max(stairs[N][0], stairs[N][1]));
	}
}
