import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_14501_퇴사 {

    static int N, days[], cost[], max;
    static boolean[] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        days = new int[N + 1];
        cost = new int[N + 1];
        v = new boolean[N + 1];

        String[] s;
        for (int i = 1; i <= N; i++) {
            s = br.readLine().split(" ");
            days[i] = Integer.parseInt(s[0]);
            cost[i] = Integer.parseInt(s[1]);
        }

        dfs(1, 0);

        System.out.print(max);
    }

    private static void dfs(int idx, int total) {
        for (int i = idx; i <= N; i++) {
            if (v[i]) continue;
            v[i] = true;
            int next = i + days[i];
            if (next > N + 1) continue; // 끝나는 날이 N보다 넘어가면 continue
            max = Math.max(max, total + cost[i]);   // 최대 수익 업데이트
            dfs(next, total + cost[i]); // 끝나는 날(다음 일수)로 이동
            v[i] = false;
        }
    }
}