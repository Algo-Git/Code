import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// [골드3] 학교 탐방하기
// MST
public class BOJ_13418_yejinKim {
    static int N, M; // 건물 개수, 도로 개수
    static ArrayList<Data1>[] arr1;
    static ArrayList<Data2>[] arr2;
    static int Min, Max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr1 = new ArrayList[N + 1];
        arr2 = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            arr1[i] = new ArrayList<>();
            arr2[i] = new ArrayList<>();
        }

        for (int i = 0; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int road = Integer.parseInt(st.nextToken());

            arr1[start].add(new Data1(end, road));
            arr1[end].add(new Data1(start, road));

            arr2[start].add(new Data2(end, road));
            arr2[end].add(new Data2(start, road));
        }

        prim1();
        prim2();

        System.out.println(Max - Min);

    }

    private static void prim1() {
        int K = 0;
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Data1> pq1 = new PriorityQueue<>();
        pq1.offer(arr1[0].get(0));
        visited[0] = true;

        int cnt = 0;
        while (!pq1.isEmpty()) {
            if (cnt == N) {
                break;
            }

            Data1 data1 = pq1.poll();

            if (visited[data1.to]) continue; // 이미 지나온 길이면 pass

            cnt++;
            visited[data1.to] = true;

            if (data1.road == 0) {
                K++;
            }

            int to = data1.to;

            for (int i = 0; i < arr1[to].size(); i++) {
                if (visited[arr1[to].get(i).to]) {
                    continue;
                } else {
                    pq1.offer(arr1[to].get(i));
                }
            }
        }
        Max = K * K;
    }

    private static void prim2() {
        int K = 0;
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Data2> pq2 = new PriorityQueue<>();
        pq2.offer(arr2[0].get(0));
        visited[0] = true;

        int cnt = 0;
        while (!pq2.isEmpty()) {
            if (cnt == N) {
                break;
            }

            Data2 data2 = pq2.poll();

            if (visited[data2.to]) continue; // 이미 지나온 길이면 pass

            cnt++;
            visited[data2.to] = true;

            if (data2.road == 0) {
                K++;
            }

            int to = data2.to;

            for (int i = 0; i < arr2[to].size(); i++) {
                if (visited[arr2[to].get(i).to]) {
                    continue;
                } else {
                    pq2.offer(arr2[to].get(i));
                }
            }
        }
        Min = K * K;
    }

    // 오르막길 먼저 정렬
    private static class Data1 implements Comparable<Data1> {
        int to;
        int road; // 오르막은 0 내리막은 1

        public Data1(int to, int road) {
            this.to = to;
            this.road = road;
        }

        @Override
        public int compareTo(Data1 o) {
            return this.road - o.road; // 오르막길 먼저
        }

        @Override
        public String toString() {
            return "Data1{" +
                    "to=" + to +
                    ", road=" + road +
                    '}';
        }
    }


    // 내리막길 먼저 출력
    private static class Data2 implements Comparable<Data2> {
        int to;
        int road; // 오르막은 0 내리막은 1

        public Data2(int to, int road) {
            this.to = to;
            this.road = road;
        }

        @Override
        public int compareTo(Data2 o) {
            return -this.road + o.road; // 내리막길 먼저
        }
    }
}
