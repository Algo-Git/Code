import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1405_미친로봇 {

    static int N;
    static double arr[], ans;
    static boolean[][] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        arr = new double[4];
        for (int i = 0; i < 4; i++) {
            arr[i] = Integer.parseInt(s[i + 1]) / 100.0;
        }

        v = new boolean[29][29];    // 상하좌우로 갈 수 있는 위치 + 로봇위치 = 총 29*29
        ans = 0.0;
        v[14][14] = true;   // 로봇 위치 (가운데) 방문체크

        dfs(14, 14, 0, 1.0);

        System.out.print(ans);

    }

    static int dr[] = {0, 0, 1, -1};
    static int dc[] = {1, -1, 0, 0};

    private static void dfs(int r, int c, int cnt, double p) {
        if (cnt == N) {
            ans += p;
            return;
        }

        for (int i = 0; i < 4; i++) {   // 동서남북
            if (v[r + dr[i]][c + dc[i]]) continue;
            v[r + dr[i]][c + dc[i]] = true;
            dfs(r + dr[i], c + dc[i], cnt + 1, p * arr[i]);
            v[r + dr[i]][c + dc[i]] = false;    // 백트래킹
        }
    }
}