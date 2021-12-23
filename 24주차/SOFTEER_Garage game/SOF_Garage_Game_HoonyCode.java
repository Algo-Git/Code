import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 구현 시뮬레이션
 * dfs bfs
 */

public class SOF_Garage_Game {

    static int N;
    static int R;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0,};

    static int[][] map;
    static int res;



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        R = 3 * N;
        map = new int[R][N];

        String[] input;
        int number;
        for (int i = 0; i < R; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                number = Integer.parseInt(input[j]);
                map[i][j] = number;
            }
        }

        dfs(0, 0);

        System.out.println(res);
    }

    private static void dfs(int dept, int sum) {

        int Max_sum = sum + (3 - dept) * 2 * (N * N);
        if (res >= Max_sum) return;


        boolean[][] v = new boolean[R][N]; //방문 체크 배열
        int[][] dup_map = new int[R][N];

        arrCopy(dup_map, map);

        int Min_x, Max_x;
        int Min_y, Max_y;

        int now; // 차고의 번호
        for (int i = 2 * N; i < 3 * N; i++) {
            for (int j = 0; j < N; j++) {
                if (v[i][j]) continue; // 방문했으면 탈출



                //초기화
                arrCopy(map, dup_map);

                now = map[i][j];
                v[i][j] = true;

                Min_x = Max_x = j; // col
                Min_y = Max_y = i; // row

                //BFS 시도
                Queue<Pair> que = new LinkedList<>();
                que.offer(new Pair(i, j));
                int count = 1; // 차고의 카운트

                Pair cur;
                while (!que.isEmpty()) {

                    cur = que.poll();
                    map[cur.y][cur.x] = 0; // 초기화
                    Min_x = Math.min(Min_x, cur.x);
                    Max_x = Math.max(Max_x, cur.x);
                    Min_y = Math.min(Min_y, cur.y);
                    Max_y = Math.max(Max_y, cur.y);


                    int row;
                    int col;
                    for (int d = 0; d < 4; d++) {
                        row = cur.y + dy[d];
                        col = cur.x + dx[d];

                        if (row < 2 * N || row >= 3 * N || col < 0 || col >= N) continue; // 범위를 벗어날때
                        if (v[row][col] || map[row][col] != now) continue;

                        v[row][col] = true;
                        que.offer(new Pair(row, col));
                        count++;
                    }
                }

                int size = (Max_x - Min_x + 1) * (Max_y - Min_y + 1);
                //벽 내리기
                if (dept < 2) {
                    for (int k = Min_x; k <= Max_x; k++) {
                        int[] block = new int[R];

                        int index = R - 1;
                        for (int l = R - 1; l > -1; l--) {
                            if (map[l][k] == 0) continue; // 0이면 기록하지 않는다
                            block[index] = map[l][k];
                            index--;
                        }
                        for (int l = R - 1; l >= index; l--) {
                            map[l][k] = block[l];
                        }
                    }
                    dfs(dept + 1, sum + count + size);
                } else { // 2일 때
                    res = Math.max(res, sum + count + size);
                }
            }
        }

    }


    private static void arrCopy(int[][] dup_map, int[][] map) {
        for (int i = 0; i < R; i++)
            for (int j = 0; j < N; j++)
                dup_map[i][j] = map[i][j];
    }


    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }


}
