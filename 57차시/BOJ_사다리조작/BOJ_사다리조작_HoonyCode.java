package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_사다리조작_HoonyCode {

    static int N, M, H;
    static int[][] map;
    static boolean flag = false;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] in = br.readLine().split(" ");
        N = Integer.parseInt(in[0]);
        M = Integer.parseInt(in[1]);
        H = Integer.parseInt(in[2]);

        map = new int[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            in = br.readLine().split(" ");
            int row = Integer.parseInt(in[0]);
            int col = Integer.parseInt(in[1]);
            // 1이면 오른쪽 방향 사다리
            // 2이면 왼쪽 방향 사다리
            map[row][col] = 1;
            map[row][col + 1] = 2;
        }

        for (int i = 0; i <= 3; i++) {
            answer = i;
            dfs(1, 0);
            if (flag) break;
        }

        System.out.println(flag ? answer : -1);
    }

    private static void dfs(int x, int count) {

        if (flag) return;

        if (answer == count) {
            if (check()) flag = true;
            return;
        }

        for (int i = x; i < H + 1; i++) {
            for (int j = 1; j < N; j++) {

                if (map[i][j] == 0 && map[i][j + 1] == 0) {
                    map[i][j] = 1;
                    map[i][j + 1] = 2;
                    dfs(i, count + 1);
                    map[i][j] = map[i][j + 1] = 0;
                }

            }
        }

    }

    private static boolean check() {

        for (int i = 1; i <= N; i++) {
            int row = 1, col = i;

            for (int j = 0; j < H; j++) {
                if (map[row][col] == 1) col++;
                else if (map[row][col] == 2) col--;
                row++;
            }

            if (col != i) return false;
        }

        return true;
    }
}
