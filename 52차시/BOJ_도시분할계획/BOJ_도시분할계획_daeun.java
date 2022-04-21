import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1647_도시분할계획 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Data> pq = new PriorityQueue<>(new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                return o1.cost - o2.cost;
            }
        });

        ArrayList<Data>[] list = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++){
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list[start].add(new Data(end, cost));
            list[end].add(new Data(start, cost));
        }

        int answer = 0;
        ArrayList<Integer> saveList = new ArrayList<>();
        boolean[] v = new boolean[N + 1];
        pq.offer(new Data(1, 0));
        while (!pq.isEmpty()) {
            Data data = pq.poll();
            int here = data.num;
            if(v[here]) continue;   // 방문한 정점이면 continue
            v[here] = true;
            saveList.add(data.cost);

            for(int i = 0; i < list[here].size(); i++){
                Data next = list[here].get(i);
                if (v[next.num]) continue;
                pq.offer(new Data(next.num, next.cost));
            }
        }

        Collections.sort(saveList);
        for(int i = 0; i < saveList.size() - 1; i++){   // 마지막 간선은 제외
            answer += saveList.get(i);
        }

        System.out.print(answer);
    }

    static class Data {
        int num, cost;

        public Data(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
}