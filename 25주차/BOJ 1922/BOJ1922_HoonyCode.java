import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class BOJ1922_HoonyCode {

    /**
     * 백준 1922 네트워크 연결
     * 시간제한 2초
     * 도현이는 컴퓨터와 컴퓨터를 모두 연결하는 네트워크 구축
     * 모든 컴퓨터를 연결하는데 필요한 최소 비용을 출력하라
     * pq Prim 구현방법
     * 1.인접리스트를 구현한다.
     * 2.현재까지 MST에 추가된 간선을 담는 PriorityQueue를 만들고, (시작하려는 정점, weight=0)을 넣어준다.
     * 3.PriorityQueue에서 가중치가 제일 작은 간선을 뺀다.
     * 4.해당 간선의 도착 지점이 이미 MST집합에 포함되어있으면 지나친다.
     * 5.아니라면 MST집합에 넣고, cnt ++ 한다. (cnt : 현재까지 MST 집합에 포함되어있는 노드 개수)
     * 6.해당 간선의 도착 지점과 연결된 노드 중 아직 MST집합에 포함되지 않은 것을 PriorityQueue에 넣는다.
     * 7.nt == 노드수가 될때까지 3-6 반복
     */

    static int N;
    static int M;
    static List<Edge>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 컴퓨터의수
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        String[] input;

        edges = new List[N + 1];

        for (int i = 0; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        int start, end, weight;
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            start = Integer.parseInt(input[0]);
            end = Integer.parseInt(input[1]);
            weight = Integer.parseInt(input[2]);
            //양방향으로 두개 넣어야 한다.
            edges[start].add(new Edge(end, weight));
            edges[end].add(new Edge(start, weight));
        }

        int answer;

        answer = Prim();

        System.out.println(answer);
    }

    private static int Prim() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        //2. 현재까지 MST에 추가된 간선을 담는 PQ를 만들고, 시작하는 정점 weight 0을 넣어준다.
        pq.add(new Edge(1, 0));

        int cnt = 0;
        int result = 0;
        boolean[] visit = new boolean[N + 1];

        Edge cur;
        while (!pq.isEmpty()) {
            //pq에서 가중치가 가장 작은 간선을 뺸다.
            cur = pq.poll();

            if (visit[cur.to]) continue;
            ;

            visit[cur.to] = true;
            result += cur.weight;

            for (Edge edge : edges[cur.to]) {
                if (!visit[edge.to]) {
                    pq.add(edge);
                }
            }

            if (++cnt == N) {
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
