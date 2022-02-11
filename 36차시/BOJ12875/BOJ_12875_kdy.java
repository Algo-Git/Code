import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N12875 {// BOJ 12875. 칙령

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());// 사람 수
        int d = Integer.parseInt(br.readLine());// 돈 차이
        int[][] rel = new int[N][N];// 친구 관계 배열
        int max = 0;// 최대 연결
        for (int i = 0; i < N; i++) {// 관계 입력받기
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                rel[i][j] = str.charAt(j) == 'Y' ? 1 : 1000;// 연결되어 있으면 1, 아니면 1000
            }
        }
        for (int k = 0; k < N; k++) {// 플로이드-와샬
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    rel[i][j] = Math.min(rel[i][j], rel[i][k] + rel[k][j]);
                }
            }
        }
        // 최대 연결 길이 구하기
        loop:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) continue;// 나->나가 무조건 최대 => 제외
                max = Math.max(max, rel[i][j]);
                if (max == 1000) break loop;// 연결되지 않은 곳 있으면 정답이 무한대 => 종료
            }
        }
        System.out.println(max == 1000 ? -1 : d * max);// 정답이 무한대면 -1, 아니면 최댓값 출력
    }
}
