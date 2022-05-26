package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_특정한최단경로_HoonyCode {

    static int N, E;
    static List<Edge>[] edges;
    static int A;
    static int B;
    private static final int MAX = 8000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] in = br.readLine().split(" ");

        N = Integer.parseInt(in[0]);
        E = Integer.parseInt(in[1]);


        edges = new List[N + 1];

        for (int i = 0; i < N + 1; i++) {
            edges[i] = new ArrayList<>();
        }

        int a, b, c;
        for (int i = 0; i < E; i++) {
            in = br.readLine().split(" ");

            a = Integer.parseInt(in[0]);
            b = Integer.parseInt(in[1]);
            c = Integer.parseInt(in[2]);

            edges[a].add(new Edge(b, c));
            edges[b].add(new Edge(a, c));
        }

        in = br.readLine().split(" ");

        A = Integer.parseInt(in[0]);
        B = Integer.parseInt(in[1]);

        int answer = 0;
        answer += extracted(1, A);
        answer += extracted(A, B);
        answer += extracted(B, N);

        int answer2 = 0;
        answer2 += extracted(1, B);
        answer2 += extracted(B, A);
        answer2 += extracted(A, N);

        int result = Math.min(answer, answer2);

        if (result >= MAX){
            System.out.println(-1);
        }else{
            System.out.println(result);
        }


    }

    private static int extracted(int start, int end) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.weight, o2.weight));
        int[] dist = new int[N + 1];
        Arrays.fill(dist, MAX);
        dist[start] = 0;
        boolean[] v = new boolean[N + 1];
        pq.offer(new Edge(start, 0));

        Edge cur;
        while (!pq.isEmpty()) {
            cur = pq.poll();

            int to = cur.to;

            if (v[to]) continue;
            v[to] = true;

            for (Edge temp : edges[to]) {
                if (dist[temp.to] > dist[to] + temp.weight) {
                    dist[temp.to] = dist[to] + temp.weight;
                    pq.offer(new Edge(temp.to, dist[temp.to]));
                }
            }
        }

        return dist[end];
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
