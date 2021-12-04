import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ1005_DP_HoonyCode {

    static int N, K, W;
    static int[] time;
    static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        String[] input;
        for (int tc = 0; tc < T; tc++) {

            // N, K 입력 받음
            input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]) + 1;
            K = Integer.parseInt(input[1]);


            //시간을 입력 받음
            time = new int[N];
            input = br.readLine().split(" ");
            for (int i = 1; i < N; i++) {
                time[i] = Integer.parseInt(input[i - 1]);
            }


            list = new List[N];
            for (int i = 1; i < N; i++) {
                list[i] = new ArrayList<Integer>();
            }

            int start;
            int end;
            for (int i = 0; i < K; i++) {
                input = br.readLine().split(" ");
                start = Integer.parseInt(input[0]);
                end = Integer.parseInt(input[1]);
                list[end].add(start);
            }

            W = Integer.parseInt(br.readLine());

            boolean[] v = new boolean[N];
            int[] dp = new int[N];

            sb.append(find(W, v, dp)).append('\n');
        }
        System.out.print(sb.toString());
    }

    private static int find(int w, boolean[] v, int[] dp) {
        if (list[w].size() == 0) return time[w];

        int res = 0;

        for (int i = 0; i < list[w].size(); i++) {
            int node = list[w].get(i);
            if (!v[node]) {
                dp[node] = find(node, v, dp);
                v[node] = true;
            }

            res = Math.max(res, dp[node]);
        }
        return res + time[w];
    }
}
