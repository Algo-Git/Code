import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11657_타임머신 {

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Edge> edges = new ArrayList<>();

        // edge 리스트에 (시작점, 도착점, 시간) 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            edges.add(new Edge(start, end ,time));
        }

        // 1번 도시로부터 나머지 도시까지의 거리 저장 배열 생성
        long[] dists = new long[N + 1];
        Arrays.fill(dists, INF);
        dists[1] = 0;

        boolean check = false;
        end:
        for (int i = 1; i <= N; i++) {  // N번 반복
            for (int j = 0; j < M; j++) {   // 간선 개수만큼
                Edge edge = edges.get(j);
                int start = edge.start;
                int end = edge.end;
                int time = edge.time;
                if (dists[start] != INF) {  // 1번 도시로부터 start번 도시까지 길이 있을 때
                    if (dists[end] > dists[start] + time) { // 최소 시간이면 업데이트
                        dists[end] = dists[start] + time;
                        // 간선은 N-1개가 최대인데, N개일때 최소값이 업데이트가 되었다면 음수 사이클 존재
                        if (i == N) {
                            check = true;
                            break end;
                        }
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        if (!check) {
            for (int i = 2; i <= N; i++) {
                if (dists[i] == INF) sb.append("-1");   // 1번 도시로부터 갈수없는 도시일때 -1
                else sb.append(dists[i]);
                sb.append('\n');
            }
        } else  // 음수 사이클 존재 시 -1 출력
            sb.append("-1");

        System.out.print(sb);
    }

    static class Edge{
        int start, end, time;

        public Edge(int start, int end, int time) {
            this.start = start;
            this.end = end;
            this.time = time;
        }
    }
}