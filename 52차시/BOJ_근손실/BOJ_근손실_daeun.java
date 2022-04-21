import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18429_근손실 {

    static int N, K, weights[], picks[], answer;
    static boolean[] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 운동 일수
        K = Integer.parseInt(st.nextToken());   // 하루 근손실량
        weights = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }
        v = new boolean[N];

        picks = new int[N];
        perm(0, 500);

        System.out.print(answer);
    }

    private static void perm(int cnt, int total) {
        if (cnt == N) {
            answer++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (v[i]) continue;
            int temp = total + weights[i] - K;
            if (temp < 500) continue;
            v[i] = true;
            picks[cnt] = i;
            perm(cnt + 1, temp);
            v[i] = false;
        }
    }
}