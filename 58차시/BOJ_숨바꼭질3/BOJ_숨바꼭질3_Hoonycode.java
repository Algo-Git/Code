package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_숨바꼭질3_Hoonycode {

    static int N, K;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        String[] in = br.readLine().split(" ");

        N = Integer.parseInt(in[0]);
        K = Integer.parseInt(in[1]);

        boolean[] v = new boolean[100_001];


        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(N, 0));


        v[N] = true;

        Pair cur;
        while (!queue.isEmpty()) {
            //가지 치기
            cur = queue.poll();

            if (K == cur.L) {
                answer = cur.time;
                break;
            }

            // 2배 검사
            for (int i = cur.L * 2; i <= 100_000; i = i * 2) {
                if (v[i]) break;
                v[i] = true;
                queue.offer(new Pair(i, cur.time));
            }

            // -1 +1
            int temp = cur.L + 1;
            if (temp >= 0 && temp < 100_001 && !v[temp]) {
                v[temp] = true;
                queue.offer(new Pair(temp, cur.time + 1));
            }

            temp = cur.L - 1;
            if (temp >= 0 && temp < 100_001 && !v[temp]) {
                v[temp] = true;
                queue.offer(new Pair(temp, cur.time + 1));
            }
        }

        System.out.println(answer);
    }

    static class Pair {
        int L; // location;
        int time;

        public Pair(int l, int time) {
            L = l;
            this.time = time;
        }
    }
}
