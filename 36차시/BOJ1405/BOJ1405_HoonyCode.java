import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1405 {

    static double[] per = new double[4]; // 확률
    static boolean[][] v = new boolean[30][30];
    static int[] dc = {1, -1, 0, 0};
    static int[] dr = {0, 0, -1, 1};
    static double answer = 0;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        N = Integer.parseInt(in[0]);
        for (int i = 0; i < 4; i++)
            per[i] = Double.parseDouble(in[i + 1]) / 100;


        v[14][14] = true;
        dfs(14, 14, 1, 0);

        System.out.printf("%.18f", answer);
    }

    private static void dfs(int row, int col, double total, int dept) {

        if (dept == N) {
            answer += total;
            return;
        }

        int drow;
        int dcol;
        for (int d = 0; d < 4; d++) {
            if (per[d] == 0) continue;
            drow = dr[d] + row;
            dcol = dc[d] + col;

            if (v[drow][dcol]) continue;

            v[drow][dcol] = true;
            dfs(drow, dcol, total * per[d], dept + 1);
            v[drow][dcol] = false;

        }

    }
}
