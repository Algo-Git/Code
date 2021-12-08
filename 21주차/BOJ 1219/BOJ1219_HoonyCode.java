import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ1219_HoonyCode {

    // N

    // 도착하는 것이 불가능할때 gg
    // 도착했을때 돈을 무한히 많이 가지고 있을 수 있다면 Gee를 출력

    static int N, start, end, M; // 도시의 수 시작 도시 도착 도시 교통 수단의 개수 M
    static int[] pay;
    static List<Edge>[] adjlist;
    private static final Long INF = 1_000_000_001L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        // 도시의 수
        N = Integer.parseInt(input[0]);
        // 시작 도시
        start = Integer.parseInt(input[1]);
        // 도착 도시
        end = Integer.parseInt(input[2]);
        // 교통 수단의 개수
        M = Integer.parseInt(input[3]);

        adjlist = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            adjlist[i] = new ArrayList<>();
        }

        int a, b, w; // 시작 도착 가중치
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            a = Integer.parseInt(input[0]);
            b = Integer.parseInt(input[1]);
            w = -Integer.parseInt(input[2]);
            adjlist[a].add(new Edge(b, w));
        }

        //각 도시에서 벌수 있는 금액
        pay = new int[N];

        input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            pay[i] = Integer.parseInt(input[i]);
        }

        BellmanFored();


    }

    private static void BellmanFored() {

        long[] dist = new long[N];
        Arrays.fill(dist, Long.MIN_VALUE);

        dist[start] = pay[start];
        long pre = 0;


        for (int i = 0; i < N + 50; i++) { // 50개 정도 있으니깐 50번 더 돌려본다
            for (int j = 0; j < N; j++) {
                //모든 정점에 대해, 연결된 간선이 존재한다면,
                //해당 정점부터 간선의 도착지까지의 최단 거리를 갱신
                for (Edge edge : adjlist[j]) {
                    //dist[j] 가 INF 면, 시작점에서 j 정점까지의 경로가 아직 없다는 뜻이므로, 갱신을 수행하지 않는다.
                    if (dist[j] != Long.MIN_VALUE && dist[edge.to] < dist[j] + edge.weight + pay[edge.to]) {
                        if (i >= N-1) pre = dist[edge.to]; // N이상에 돌면 사이클이 존재한다.
                        dist[edge.to] = dist[j] + edge.weight + pay[edge.to];
                        if (i >= N-1 && pre < dist[edge.to]) dist[edge.to] = INF; // N이상에 돌면 사이클이 존재한다.
                    }
                }
            }
        }

        if (dist[end] == Long.MIN_VALUE) {
            System.out.print("gg");
        }else if(dist[end] >= INF){
            System.out.print("Gee");
        }else{
            System.out.print(dist[end]);
        }

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
