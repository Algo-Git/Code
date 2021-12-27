package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// [골드4] 네트워크 연결
// MST 알고리즘 : PRIM 알고리즘 이용
public class BOJ_1992_yejinKim {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        ArrayList<Data>[] adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from= Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[from].add(new Data(to, weight));
            adjList[to].add(new Data(from, weight));
        }


        int[] Dist = new int[N + 1];
        for (int i = 2; i <= N; i++) {
            Dist[i] = 10000000;
        }
        boolean[] visited = new boolean[N + 1];

        // 1번을 시작점으로
        int cnt = 0;
        int total = 0;
        while (true) {
            int min = 1000000;
            int cur = -1;
            for (int i = 1; i <= N; i++) {
                if (!visited[i] && min > Dist[i]) {
                    min = Dist[i];
                    cur = i;
                }
            }
            visited[cur] = true;

            cnt++;
            total += min;

            if (cnt == N) {
                break;
            }

            for (int i = 0; i < adjList[cur].size(); i++) {
                Data data = adjList[cur].get(i);
                if (!visited[data.to] && Dist[data.to] > data.weight) {
                    Dist[data.to] = data.weight;
                }
            }

        }

        System.out.println(total);

    }

    private static class Data {
        int to;
        int weight;

        Data(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
