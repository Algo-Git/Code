package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_근손실_HoonyCode {

    static int N, K;
    static int answer = 0;
    static int[] kit;
    static boolean[] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //500 하루가 지날 떄마다 중량이 K만큼 감소
        //가만히 있으면 중량이 감소
        // 각각 운동 키트는 N일 동안 한번 씩만 사용할 수 있다
        String[] in = br.readLine().split(" ");

        N = Integer.parseInt(in[0]);
        K = Integer.parseInt(in[1]);

        kit = new int[N];
        v = new boolean[N];

        in = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            kit[i] = Integer.parseInt(in[i]) - K;
        }

        dfs(0, 500);

        System.out.println(answer);
    }

    private static void dfs(int depth, int total) {
        if (total < 500) return;

        if (depth == N) {
            answer++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (v[i]) continue;
            v[i] = true;
            dfs(depth + 1, total + kit[i]);
            v[i] = false;
        }
    }
}
