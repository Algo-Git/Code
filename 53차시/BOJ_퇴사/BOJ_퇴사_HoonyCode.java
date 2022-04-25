package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//dfs 풀이법
public class BOJ_퇴사_HoonyCode {

    static Pair[] pairs;

    static int answer = 0;

    static int N;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        String[] in;

        pairs = new Pair[N];

        for (int i = 0 ; i < N ; i++){
            in = br.readLine().split(" ");
            pairs[i] = new Pair(Integer.parseInt(in[0]), Integer.parseInt(in[1]));
        }

        dfs(0, 0);

        System.out.println(answer);
    }

    private static void dfs(int depth, int total) {
        if (depth > N) return;

        if (depth == N){
            answer = Math.max(answer, total);
            return;
        }

        dfs(depth + pairs[depth].time, total + pairs[depth].val);
        dfs(depth + 1, total);
    }

    static class Pair{
        int time;
        int val;

        public Pair(int time, int val) {
            this.time = time;
            this.val = val;
        }
    }
}
