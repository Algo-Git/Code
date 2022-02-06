import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


public class BOJ1079 {

    static int N;
    static int[] guilty;
    static int[][] R;
    static int unjin;
    static int answer = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        guilty = new int[N];

        String[] in = br.readLine().split(" ");
        // 유죄 지수 받아드리기
        for (int i = 0; i < N; i++) {
            guilty[i] = Integer.parseInt(in[i]);
        }


        R = new int[N][N];

        for (int i = 0; i < N; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                R[i][j] = Integer.parseInt(in[j]);
            }
        }

        unjin = Integer.parseInt(br.readLine());


        boolean flag = false;
        // 짝수 일 때
        if ((N & 1) == 0) {
            flag = true;
        }

        // flag : true 밤 false : 아침

        solve(flag, 0, new boolean[N], N);

        System.out.println(answer);
    }

    private static void solve(boolean flag, int cnt, boolean[] v, int pcnt) {
        // 은진이가 죽었거나 은진이 혼자 남았을 떄
        if (v[unjin] || pcnt == 1) {

            answer = Math.max(answer, cnt);
            return;
        }

        if (flag) { // 밤 일때
            for (int i = 0; i < N; i++) {
                if (i == unjin || v[i]) continue; // 은진이거나 죽은 사람이면 continue;
                v[i] = true;

                //죽은 사람 처리
                for (int j = 0; j < N; j++) {
                    guilty[j] += R[i][j];
                }
                solve(!flag, cnt + 1, v, pcnt - 1);
                //백트래킹
                for (int j = 0; j < N; j++) {
                    guilty[j] -= R[i][j];
                }
                v[i] = false;
            }


        } else { // 낮 일떄
            int maxGuilty = -1;
            int maxGuiltyCnt = -1000;

            for (int i = 0; i < N; i++) {
                if (v[i]) continue;;
                if (maxGuiltyCnt < guilty[i]) {
                    maxGuilty = i;
                    maxGuiltyCnt = guilty[i];
                }
            }
            v[maxGuilty] = true; // 죽음
            solve(!flag, cnt, v, pcnt - 1);
            //백트래킹
            v[maxGuilty] = false;
        }


    }

}
