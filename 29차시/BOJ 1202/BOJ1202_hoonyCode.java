import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ1202 {

    static int N, K; // N : 보석의 개수 K : 가방의 개수
    static int[] backpack;
    static Jewel[] jewels;
    static PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] in = br.readLine().split(" ");
        N = Integer.parseInt(in[0]);
        K = Integer.parseInt(in[1]);

        backpack = new int[K];
        jewels = new Jewel[N];


        for (int i = 0; i < N; i++) {
            in = br.readLine().split(" ");
            jewels[i] = new Jewel(Integer.parseInt(in[0]), Integer.parseInt(in[1]));
        }

        for (int i = 0; i < K; i++) {
            backpack[i] = new Integer(br.readLine());
        }

        //보석 무게 기준으로 정렬
        Arrays.sort(jewels, (o1, o2) -> Integer.compare(o1.M, o2.M));
        Arrays.sort(backpack);

        long answer = 0;

        int idx = 0;
        //무게 제한이 낮은 가방부터 최대한 비싼 보석을 넣는 방법
        for (int i = 0; i < K; i++) {

            while (idx < N && jewels[idx].M <= backpack[i]) {
                pq.offer(jewels[idx++].V);
            }

            if (!pq.isEmpty()) {
                answer += pq.poll();
            }

        }


        System.out.println(answer);

    }


    static class Jewel {
        int M; //무게
        int V; //가격

        public Jewel(int m, int v) {
            M = m;
            V = v;
        }
    }
}
