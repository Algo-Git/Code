```java
import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        List<Edge>[] edges = new List[n+1];

        for (int i = 0 ; i < n ; i++){
            edges[i] = new ArrayList<>();
        }

        for (int[] cost : costs){
            edges[cost[0]].add(new Edge(cost[1], cost[2]));
            edges[cost[1]].add(new Edge(cost[0], cost[2]));
        }


        int answer = Prim(edges,n);
        return answer;
    }
    
    private int Prim(List<Edge>[] edges, int N) {
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

            if (++cnt == N) {
                break;
            }
        }
        return result;
    }
}



 class Edge implements Comparable<Edge>{
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
```

