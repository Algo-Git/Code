import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;


public class BOJ14220 {

    static long dp[][];
    static List<Graph>[] graph;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        dp = new long[n][2];
        graph = new List[n];


        for (int i = 0; i < n; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }

        String[] in;
        int weight;
        for (int i = 0; i < n; i++) {
            in = br.readLine().split(" ");
            graph[i] = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                weight = Integer.parseInt(in[j]);
                if (weight == 0) continue;
                graph[i].add(new Graph(j, weight));
                dp[j][0] = min(dp[j][0], weight);
            }
        }

        long answer = solve();
        System.out.println(answer);

    }

    private static long solve() {
        if (n == 1)
            return 0;

        //k : 이동 횟수

        for (int k = 2; k < n; k++) {
            for (int i = 0; i < n; i++) {
                dp[i][1] = Integer.MAX_VALUE;
            }
            for (int i = 0 ; i < n ; i++){
                for (Graph next : graph[i]){
                    dp[next.edge][1] = min(dp[next.edge][1], dp[i][0] + next.weight);
                }
            }
            for (int i = 0 ; i < n ; i++){
                dp[i][0] = dp[i][1];
            }
        }

        long result = Integer.MAX_VALUE;

        for (int i = 0 ; i <n ; i++){
            result = min(result,dp[i][0]);
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    static class Graph {
        int edge;
        int weight;

        public Graph(int edge, int weight) {
            this.edge = edge;
            this.weight = weight;
        }
    }
}