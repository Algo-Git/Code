import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, dir, x, y;
    static int[][] map, scoBoard;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[] dice;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dir = 1;
        x = 0;
        y = 0;
        dice = new int[]{1, 6, 3, 4, 5, 2};
        int score = 0;
        scoBoard = new int[N][M];
        v = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (v[i][j]) continue;
                setScore(j, i);
            }
        }
        for (int i = 0; i < K; i++) {
            roll();
            score += scoBoard[y][x];
            setDir();
        }
        System.out.println(score);
    }

    private static void setDir() {
        if (dice[1] > map[y][x]) dir = (dir + 1) % 4;
        else if (dice[1] < map[y][x]) dir = (dir + 3) % 4;
    }

    private static void setScore(int r, int c) {
        int sco = map[c][r];
        Queue<XY> q = new LinkedList<>();
        Queue<XY> cq = new LinkedList<>();
        q.offer(new XY(r, c));
        cq.offer(new XY(r, c));
        v[c][r] = true;
        int cnt = 0;
        while (!q.isEmpty()) {
            XY cur = q.poll();
            cnt += 1;
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || ny < 0 || ny >= N || nx >= M || v[ny][nx] || map[ny][nx] != sco) {
                    continue;
                }
                v[ny][nx] = true;
                q.offer(new XY(nx, ny));
                cq.offer(new XY(nx, ny));
            }
        }
        while (!cq.isEmpty()) {
            XY cur = cq.poll();
            scoBoard[cur.y][cur.x] = cnt * sco;
        }
    }

    private static void roll() {
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        if (nx < 0 || ny < 0 || ny >= N || nx >= M) {
            dir = (dir + 2) % 4;
        }
        x += dx[dir];
        y += dy[dir];
        int tmp = dice[0];
        switch (dir) {
            case 0:
                dice[0] = dice[4];
                dice[4] = dice[1];
                dice[1] = dice[5];
                dice[5] = tmp;
                break;
            case 1:
                dice[0] = dice[3];
                dice[3] = dice[1];
                dice[1] = dice[2];
                dice[2] = tmp;
                break;
            case 2:
                dice[0] = dice[5];
                dice[5] = dice[1];
                dice[1] = dice[4];
                dice[4] = tmp;
                break;
            case 3:
                dice[0] = dice[2];
                dice[2] = dice[1];
                dice[1] = dice[3];
                dice[3] = tmp;
                break;
        }
    }

    static class XY {
        int x, y;

        public XY(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}