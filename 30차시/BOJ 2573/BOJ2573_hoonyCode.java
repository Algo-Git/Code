import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 완탐 문제
public class BOJ2573 {

    static int R, C;
    static int[][] map;
    static int[][] subMap;
    static boolean[][] v;
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        R = Integer.parseInt(in[0]);
        C = Integer.parseInt(in[1]);

        map = new int[R][C];
        subMap = new int[R][C];
        v = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(in[j]);
            }
        }


        int bfscnt = 0;
        while (true) {
            bfscnt = 0;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] < 1 || v[i][j]) continue;
                    bfs(new Pair(i, j));
                    bfscnt++;
                }
            }


            if (bfscnt == 0) {
                answer = 0;
                break;
            }

            if (bfscnt > 1)
                break;

            // map -> submap and 초기화 submap and v
            mapCopyAndInit();
            answer++;
        }

        System.out.println(answer);

    }

    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};

    private static void mapCopyAndInit() {

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = subMap[i][j];
                subMap[i][j] = 0;
                v[i][j] = false;
            }
        }
    }

    private static void bfs(Pair start) {
        Queue<Pair> que = new LinkedList<>();
        que.offer(start);
        v[start.row][start.col] = true;

        Pair cur;
        int drow;
        int dcol;
        int cnt;
        while (!que.isEmpty()) {
            cur = que.poll();

            cnt = 0;
            for (int d = 0; d < 4; d++) {
                drow = dr[d] + cur.row;
                dcol = dc[d] + cur.col;

                // 범위를 벗어나거나 map[drow][docl] < 1 보다 작으면
                if (drow < 0 || dcol < 0 || drow >= R || dcol >= C || map[drow][dcol] < 1) {
                    cnt++;
                    continue;
                }

                if (v[drow][dcol]) continue;

                //아닐때
                que.offer(new Pair(drow, dcol));
                v[drow][dcol] = true;
            }

            //서브맵에 물 주위를 빼서 submap에 저장
            subMap[cur.row][cur.col] = map[cur.row][cur.col] - cnt;
        }

    }

    static class Pair {
        int row, col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
