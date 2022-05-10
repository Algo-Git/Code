package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_타임머신_HoonyCode {

    static int N, M;
    static List<Edge>[] adjlist;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        adjlist = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adjlist[i] = new ArrayList<>();
        }

        int from, to, weight;
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            from = Integer.parseInt(input[0]);
            to = Integer.parseInt(input[1]);
            weight = Integer.parseInt(input[2]);
            adjlist[from].add(new Edge(to, weight));
        }

        bellmanford();

    }

    private static void bellmanford() {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[1] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (Edge edge : adjlist[j]) {
                    if (dist[j] != Integer.MAX_VALUE && dist[edge.to] > dist[j] + edge.weight) {
                        dist[edge.to] = dist[j] + edge.weight;
                        if (i == N) {
                            System.out.println(-1);
                            return;
                        }
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                sb.append(-1).append('\n');
            else
                sb.append(dist[i]).append('\n');
        }
        System.out.print(sb.toString());

    }

    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
