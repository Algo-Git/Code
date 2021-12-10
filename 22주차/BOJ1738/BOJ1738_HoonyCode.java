import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1738_HoonyCode {
    //골드 2
    //벨만포드 문제

    /**
     * 문제의 이해
     * 민승이는 놀러간다.
     * 집에서 -> 콘도까지 가기위해서 골목길을 통과
     * 어떤 길 깡패 만나면 - (금품 갈취)
     * 어떤 길 금품들이 떨어져 있다. 주우면 +
     * 민승이가 최대한 유리한 경로를 따라 집에서 코레스코 콘도까지 가기 위해서 어떻게 해야할까?
     */

    static int n; // 골목길들이 교차하는 지점의 개수
    static int m; // 골목길의 개수
    static List<Edge>[] adjList;
    static Stack<Integer> Road;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        adjList = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<Edge>();
        }

        int u, v, w; // u번 교차점에서 v번 교차점으로 이동할 수 잇는 골목길이 나있다는 의미
        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            u = Integer.parseInt(input[0]);
            v = Integer.parseInt(input[1]);
            w = Integer.parseInt(input[2]);
            adjList[u].add(new Edge(v, w));
        }

        BellmanFord();

    }

    private static void BellmanFord() {
        long[] dist = new long[n + 1];
        int[] preEdge = new int[n + 1];
        Arrays.fill(dist, -Integer.MAX_VALUE);
        // 민승이의 집은 1 도착점은 n
        dist[1] = 0;
        boolean flag = false;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (Edge edge : adjList[j]) {
                    if (dist[edge.to] < dist[j] + edge.weight) {
                        preEdge[edge.to] = j;
                        dist[edge.to] = dist[j] + edge.weight;
                        if (i == n && bfs(edge.to)) { // n번째에서 해당하는 사이클이 -> 출구에 영향을 미치면 -1
                            System.out.println(-1);
                            return;
                        }
                    }
                }
            }
        }

        if (dist[n] == -Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            //경로 찾기
            road_Search(preEdge);
            StringBuilder sb = new StringBuilder();
            while (!Road.isEmpty()) {
                sb.append(Road.pop()).append(' ');
            }
            System.out.println(sb.toString());
        }
    }

    // 저번에 예진님이 설명하신 길찾기 부분
    private static boolean bfs(int start){
        if (start == n) return  true;
        Queue<Integer> que = new LinkedList<>();
        boolean[] v = new boolean[n+1];
        que.offer(start);
        v[start] = true;

        int cur;
        while (!que.isEmpty()){
            cur = que.poll();
            if (cur == n){
                return true;
            }
            for (Edge edge : adjList[start]){
                if (v[edge.to]) continue;
                v[edge.to] = true;
                que.offer(edge.to);
            }
        }

        return false;
    }

    static void road_Search(int[] preEdge) {
        Road = new Stack<Integer>();

        int now = n;
        Road.add(now);

        while (now != 1) {
            now = preEdge[now];
            Road.add(now);
        }
    }

    static class Edge {
        int to; // 이동할 지점
        int weight; // 가중치

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
