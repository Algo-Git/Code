import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1238 {

    //플로이드 와샬
    static int N, M, X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        X = Integer.parseInt(input[2]) - 1;

        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = 1000001; // 최대값 셋팅 초기화
            }
        }

        map[X][X] = 0; // 자신의 동네에서 하는거 0으로 만들어줌

        int row, col, val;
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            row = Integer.parseInt(input[0]) - 1;
            col = Integer.parseInt(input[1]) - 1;
            val = Integer.parseInt(input[2]);
            if (map[row][col] > val) map[row][col] = val;
        }

        //플로이드 와샬
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] > map[i][k] + map[k][j]) map[i][j] = map[i][k] + map[k][j];
                }
            }
        }

        int res = 0;
        for (int i = 0; i < N; i++) {
            if (res < map[i][X] + map[X][i]) res = map[i][X] + map[X][i];
        }

        System.out.println(res);
    }
}
