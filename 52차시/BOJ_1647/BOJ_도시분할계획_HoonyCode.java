package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_도시분할계획_HoonyCode {

    static int N, M;
    static int[] parents;
    static Edge[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //유지비의 합을 최소로 하고 싶다 -> MST 알고리즘
        String[] in = br.readLine().split(" ");

        N = Integer.parseInt(in[0]);
        M = Integer.parseInt(in[1]);

        // 마을은 1부터 시작
        parents = new int[N + 1];
        edges = new Edge[M];

        for (int i = 0; i < M; i++) {
            in = br.readLine().split(" ");
            edges[i] = new Edge(Integer.parseInt(in[0]), Integer.parseInt(in[1]), Integer.parseInt(in[2]));
        }

        Arrays.sort(edges, (o1, o2) -> Integer.compare(o1.weight, o2.weight));

        init(); // 초기화
        int cnt = 0;
        long answer = 0;
        for (int i = 0 ; i < M ; i++){
            if (union(edges[i].to, edges[i].next)) continue; // 연결되어 있다면 continue;
            //연결되어 있지 않다면
            answer += edges[i].weight;
            cnt++;
            if (cnt == N-2) break;
        }

        System.out.println(answer);
    }

    private static int fine(int x){
        if (parents[x] == x) return x;

        return parents[x] = fine(parents[x]);
    }

    private static boolean union(int x, int y){
        x = fine(x);
        y = fine(y);

        if (x == y) return true;

        // 연결되어 있지 않을 때
        parents[y] = x;

        return false;
    }

    static class Edge {
        int to;
        int next;
        int weight;

        public Edge(int to, int next, int weight) {
            this.to = to;
            this.next = next;
            this.weight = weight;
        }
    }

    private static void init() {

        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }
}
