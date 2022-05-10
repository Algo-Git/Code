package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_택배_HoonyCode {

    static int n, m;
    static int[][] map;
    static int[][] pointMap;

    private static final int MAX = 200 * 10000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] in = br.readLine().split(" ");

        n = Integer.parseInt(in[0]);
        m = Integer.parseInt(in[1]);

        map = new int[n + 1][n + 1];
        pointMap = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(map[i], MAX);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                pointMap[i][j] = j;
            }
        }

        int A, B, W;
        for (int i = 0; i < m; i++) {
            in = br.readLine().split(" ");
            A = Integer.parseInt(in[0]);
            B = Integer.parseInt(in[1]);
            W = Integer.parseInt(in[2]);

            map[B][A] = map[A][B] = Math.min(map[A][B], W);
        }


        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                        pointMap[i][j] = pointMap[i][k];
                    }
                }
            }
        }


        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    sb.append('-').append(' ');
                    continue;
                }
                sb.append(pointMap[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

}
