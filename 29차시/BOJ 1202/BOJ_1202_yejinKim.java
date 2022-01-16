import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// [골드2] 보석 도둑

// 작은 가방부터 오름차순으로 정렬해 놓는다.
// 가능한 무게 이하 중에서 가장 가치가 높은 보석을 넣어야 한다.
public class BOJ_1202_yejinKim {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 보석 개수
        int K = Integer.parseInt(st.nextToken()); // 가방 개수

        Jewel[] jewels = new Jewel[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(m, v);
        }

        // 보석의 무게에 따라서 정렬한다.
        Arrays.sort(jewels, (o1, o2) -> o1.m - o2.m);

        for(int i = 0; i<N; i++){
            System.out.println(jewels[i]);
        }

        int[] C = new int[K]; // 각 가방에 담을 수 있는 최대 무게
        for (int i = 0; i < K; i++) {
            C[i] = Integer.parseInt(br.readLine());
        }

        // 가방은 최대무게 가 가벼운 순으로
        Arrays.sort(C);

        // pq는 가치가 높은 순으로
        PriorityQueue<Jewel> pq = new PriorityQueue<>();


        long answer = 0;
        int idx = 0;
        for(int i = 0;i < K; i++){
            while(true){
                if(idx >= N) break;
                if(C[i] < jewels[idx].m) break; // 가방무게보다 보석무게가 무거우면 break;
                pq.offer(jewels[idx]);
                idx ++;
            }

            if(!pq.isEmpty()) {
                answer += pq.poll().v;
            }
        }

        System.out.println(answer);
    }

    private static class Jewel implements Comparable<Jewel> {
        int m;
        int v;

        @Override
        public String toString() {
            return "Jewel{" +
                    " m=" + m +
                    ", v=" + v +
                    '}';
        }

        public Jewel(int m, int v) {
            this.m = m;
            this.v = v;
        }

        // 가치 높은 순
        @Override
        public int compareTo(Jewel o) {
            return -(this.v - o.v);
        }
    }
}
