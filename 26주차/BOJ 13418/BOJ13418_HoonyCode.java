import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class BOJ13418 {
    static int N;
    static int M;
    static List<Edge>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 컴퓨터의수
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);


        edges = new List[N + 1];

        for (int i = 0; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        int start, end, weight;
        for (int i = 0; i < M + 1; i++) {
            input = br.readLine().split(" ");
            start = Integer.parseInt(input[0]);
            end = Integer.parseInt(input[1]);
            weight = (Integer.parseInt(input[2]) == 0) ? 1 : 0;
            //양방향으로 두개 넣어야 한다.
            edges[start].add(new Edge(end, weight));
            edges[end].add(new Edge(start, weight));
        }

        int r1 = Prim();
        int r2 = MaxPrim();

        int answer = r2 * r2 - r1 * r1;

        System.out.println(answer);
    }

    private static int MaxPrim() {
        PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o2.weight - o1.weight;
            }
        });
        //2. 현재까지 MST에 추가된 간선을 담는 PQ를 만들고, 시작하는 정점 weight 0을 넣어준다.
        pq.add(new Edge(0, 0));

        int cnt = 0;
        int result = 0;
        boolean[] visit = new boolean[N + 1];

        Edge cur;
        while (!pq.isEmpty()) {
            //pq에서 가중치가 가장 작은 간선을 뺸다.
            cur = pq.poll();

            if (visit[cur.to]) continue;

            visit[cur.to] = true;
            result += cur.weight;

            for (Edge edge : edges[cur.to]) {
                if (!visit[edge.to]) {
                    pq.add(edge);
                }
            }

            if (++cnt == N + 1) {
                break;
            }
        }

        return result;

    }

    private static int Prim() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        //2. 현재까지 MST에 추가된 간선을 담는 PQ를 만들고, 시작하는 정점 weight 0을 넣어준다.
        pq.add(new Edge(0, 0));

        int cnt = 0;
        int result = 0;
        boolean[] visit = new boolean[N + 1];

        Edge cur;
        while (!pq.isEmpty()) {
            //pq에서 가중치가 가장 작은 간선을 뺸다.
            cur = pq.poll();

            if (visit[cur.to]) continue;

            visit[cur.to] = true;
            result += cur.weight;

            for (Edge edge : edges[cur.to]) {
                if (!visit[edge.to]) {
                    pq.add(edge);
                }
            }

            if (++cnt == N + 1) {
                break;
            }
        }

        return result;

    }


    static class Edge implements Comparable<Edge> {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(weight, o.weight);
        }
    }
}