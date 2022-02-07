import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1079_마피아 {

    static int N, C[], R[][], eunjin, res;
    static boolean[] isDead;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());    // 참가자 수
        C = new int[N]; // 유죄 지수
        R = new int[N][N];  // 참가자 반응 N*N 배열

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            C[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                R[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        eunjin = Integer.parseInt(br.readLine());   // 은진이의 참가 번호
        isDead = new boolean[N];

        mafia(N, 0);

        System.out.print(res);
    }

    private static void mafia(int cnt, int day) {

        // 은진이가 죽었거나 1명이 남았을 때 return
        if (isDead[eunjin] || cnt == 1) {
            res = Math.max(res, day);
            return;
        }

        if ((cnt & 1) == 0) {   // 짝수 => 밤 => 마피아가 사람 죽임
            for (int i = 0; i < N; i++) {
                if (i == eunjin || isDead[i]) continue; // 은진이거나 죽은사람이면 continue

                isDead[i] = true;
                for (int j = 0; j < N; j++) {
                    if (isDead[j]) continue;
                    C[j] += R[i][j];
                }

                mafia(cnt - 1, day + 1);    // 한명 빼기, 밤이므로 날짜 + 1

                for (int j = 0; j < N; j++) {   // 백트래킹 !!
                    if (isDead[j]) continue;
                    C[j] -= R[i][j];
                }
                isDead[i] = false;
            }
        } else {  // 홀수 => 낮 => 유죄지수 가장 높은 사람 죽임

            // 죽일 사람 인덱스 구하기
            int killIdx = 0, maxScore = 0;

            for (int i = 0; i < N; i++) {
                if (isDead[i]) continue;
                if (maxScore < C[i]) {
                    maxScore = C[i];
                    killIdx = i;
                }
            }

            isDead[killIdx] = true;
            mafia(cnt - 1, day);    // 한명 빼기, 낮이므로 날짜 그대로
            isDead[killIdx] = false;
        }
    }
}