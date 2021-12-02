import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class BOJ1238_DIS {

    static int N, M, X;
    static List<Edge>[] adj;
    static int[] dist;
    static int[] reverseDist;
    static int[] res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input;
        input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        X = Integer.parseInt(input[2]);
        adj = new ArrayList[N + 1];
        dist = new int[N + 1];
        res = new int[N + 1];
        reverseDist = new int[N + 1];


        //인접 리스트 생성
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<Edge>();
        }

        //입력 받기
        int start, end, weight;
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            start = Integer.parseInt(input[0]);
            end = Integer.parseInt(input[1]);
            weight = Integer.parseInt(input[2]);

            adj[start].add(new Edge(end, weight));
        }

        for (int i = 1; i <= N; i++) {
            dijkstra(i);
        }

        int result = 0;
        for (int i = 1 ; i <= N; i++){
            result = Math.max(result, reverseDist[i] + res[i]);
        }

        System.out.println(result);


    }

    static int dijkstra(int start) {
        boolean[] v = new boolean[N + 1];
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        Arrays.fill(dist, Integer.MAX_VALUE);
        priorityQueue.add(new Edge(start, 0));
        dist[start] = 0;

        Edge edge;
        int destination;
        while (!priorityQueue.isEmpty()) {
            edge = priorityQueue.poll();
            destination = edge.destination;
            if (v[destination]) continue;

            v[destination] = true;
            for (Edge next : adj[destination]) {
                if (dist[next.destination] >= dist[destination] + next.weight) {
                    dist[next.destination] = dist[destination] + next.weight;
                    priorityQueue.add(new Edge(next.destination, dist[next.destination]));
                }
            }

            if (start == X) {
                reverseDist = Arrays.copyOf(dist,N+1);
            } else
                res[start] = dist[X];


        }

        return 0;
    }


    static class Edge implements Comparable<Edge> {

        int destination;
        int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

}
