import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_24270_미니버킷리스트 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int K = Integer.parseInt(s[1]);
        int[] S = new int[N];

        int total = 0, cnt = 0;
        s = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(s[i]);
            total += S[i];
            cnt++;
        }

        int time = 1;
        if (total < K) {
            time = K - total;   // 일 없음의 단위시간 구하기
            cnt += time;
        }

        long res = 1;
        for (int i = time + 1; i <= cnt; i++) {
            res = (res % 1_000_000_007) * i;
        }

        System.out.print(res % 1_000_000_007);
    }
}