import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573_yejinKim {
    static int[][] map;
    static int N, M;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];


        int answer = 0;
        int cnt = 0; // 현재 빙산의 얼음 개수
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) cnt++;
            }
        }

        while (true) {
            // 얼음이 하나도 없으면
            if (cnt == 0) {
                answer = 0;
                break;
            }
//            System.out.println(cnt);

            Queue<Data> queue = new LinkedList<>();
            boolean[][] visited = new boolean[N][M];

            int cnt2 = 0;
            // 얼음세기를 시작하는 곳
            outer:
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] != 0) {
                        visited[i][j] = true;
                        queue.offer(new Data(i, j));
                        cnt2++;
                        break outer;
                    }
                }
            }

            // 붙어있는 얼음의 개수
            while (!queue.isEmpty()) {
                Data data = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = data.x + dx[d];
                    int ny = data.y + dy[d];
                    if (visited[nx][ny] || map[nx][ny] == 0) continue;
                    visited[nx][ny] = true;
                    queue.offer(new Data(nx, ny));
                    cnt2++;
                }
            }


            if (cnt != cnt2) { // 얼음 개수가 다르면
                break;
            }

            answer++;

            int[][] minus = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] != 0) {
                        int m = 0;
                        for (int d = 0; d < 4; d++) {
                            int nx = i + dx[d];
                            int ny = j + dy[d];
                            if (map[nx][ny] == 0) m++;
                        }
                        minus[i][j] += m;
                    }
                }
            }

            cnt = 0;
            for (int i = 0; i < N; i++) { // 녹고 나서의 얼음 개수
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 0) continue;
                    map[i][j] -= minus[i][j];
                    if (map[i][j] <= 0) map[i][j] = 0;
                    else cnt++;
                }
            }


//            System.out.println("cnt: " + cnt + ", cnt2: " + cnt2);
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < M; j++) {
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }

        }
        System.out.println(answer);

    }

    private static class Data {
        int x;
        int y;

        public Data(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
