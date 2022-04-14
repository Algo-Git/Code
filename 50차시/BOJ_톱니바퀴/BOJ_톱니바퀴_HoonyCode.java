package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_톱니바퀴_HoonyCode {

    static int[] dx = {-1, 1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 톱니바퀴
        int[][] spins = new int[4][8];
        int answer = 0;
        String[] in;

        for (int i = 0; i < 4; i++) {
            in = br.readLine().split("");
            for (int j = 0; j < 8; j++) {
                spins[i][j] = Integer.parseInt(in[j]);
            }
        }


        // 회전 횟수
        int K = Integer.parseInt(br.readLine());

        int N;
        int S;

        boolean[] v = new boolean[4];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < K; i++) {
            in = br.readLine().split(" ");
            N = Integer.parseInt(in[0]) - 1;
            S = Integer.parseInt(in[1]); // 1시게 -1 반시계 // 0 멈춤

            Arrays.fill(v, false);
            queue.clear();
            queue.offer(new int[]{N, S});
            v[N] = true;

            int[] cur;
            while (!queue.isEmpty()) {
                cur = queue.poll();

                int nx;
                for (int d = 0; d < 2; d++) {
                    nx = cur[0] + dx[d];

                    if (nx < 0 || nx > 3) continue;
                    if (v[nx]) continue;
                    v[nx] = true;
                    //왼쪽일 떄
                    if (d == 0) {
                        if (spins[nx][2] == spins[cur[0]][6]) continue;
                        queue.offer(new int[]{nx, -cur[1]});
                    } else {//오른쪽 일 떄
                        if (spins[nx][6] == spins[cur[0]][2]) continue;
                        queue.offer(new int[]{nx, -cur[1]});
                    }
                }

                rotate(spins[cur[0]], cur[1]);
            }
        }


        for (int i = 0; i < 4; i++) {
            answer += (spins[i][0] << i);
        }

        System.out.println(answer);

    }


    public static void rotate(int[] spins, int state) {
        if (state == 1) { // 시계방향

            int temp = spins[7];

            for (int i = 7; i > 0; i--) {
                spins[i] = spins[i - 1];
            }

            spins[0] = temp;

        } else { // 반시계 방향

            int temp = spins[0];

            for (int i = 0; i < 7; i++) {
                spins[i] = spins[(i + 1)];
            }

            spins[7] = temp;
        }
    }
}
