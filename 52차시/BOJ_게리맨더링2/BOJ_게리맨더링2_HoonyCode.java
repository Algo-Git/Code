package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_게리맨더링2_HoonyCode {

    static boolean[] v;
    static int[][] map;
    static int N;
    static int total = 0;
    static int[] val;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        init(); // 초화 함수

        // x row y col

        for (int x = 1; x < N - 1; x++) {
            for (int y = 1; y < N - 1; y++) {
                for (int d1 = 1; d1 < N - 1; d1++) {
                    for (int d2 = 1; d2 < N - 1; d2++) {
                        if (x + d1 + d2 >= N || y - d1 < 0 || y + d2 >= N) continue;
                        solve(x, y, d1, d2);
                    }
                }
            }
        }


        System.out.println(answer);
    }

    private static void solve(int x, int y, int d1, int d2) {

        Arrays.fill(val, 0);

        //1번 구역
        int t = y;
        for (int i = 0; i < x + d1; i++) {
            if (i >= x) t--;
            for (int j = 0; j <= t; j++) {
                val[0] += map[i][j];
            }
        }

        t = y + 1;
        //2번 구역
        for (int i = 0; i <= x + d2; i++) {
            if (i > x) t++;
            for (int j = t; j < N; j++) {
                val[1] += map[i][j];
            }
        }

        t = y - d1 + d2;
        //3번구역
        for (int i = N - 1; i >= x + d1; i--) {
            if (i < x + d1 + d2) t--;
            for (int j = 0; j < t; j++) {
                val[2] += map[i][j];
            }
        }


        t = y - d1 + d2;
        //4번 구역
        for (int i = N - 1; i > x + d2; i--) {
            if (i <= x + d1 + d2) t++;
            for (int j = t; j < N; j++) {
                val[3] += map[i][j];
            }
        }

        //5번 구역
        val[4] = total - val[0] - val[1] - val[2] - val[3];

        Arrays.sort(val);

        answer = Math.min(answer, val[4] - val[0]);
    }

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(in[j]);
                total += map[i][j];
            }
        }

        val = new int[5];
    }

}
