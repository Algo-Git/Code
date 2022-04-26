package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_주사위굴리기2_HoonyCode {

    static int N, M;
    static int K;
    static int[][] map;
    static int[] dice = new int[]{6, 3, 5, 4, 2, 1}; // 아래 동 남 서 북 위


    // 동 남 서 북
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    //점수
    static int answer = 0;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        N = Integer.parseInt(in[0]);
        M = Integer.parseInt(in[1]);
        K = Integer.parseInt(in[2]);

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(in[j]);
            }
        }

        //(r,c) row col; 가장 왼쪽 위에 있는 칸의 좌표는 1,1 가장 오른쪽 아래이 있는 칸의 좌표는 N, M
        // 지도 위에 윗 면이 1이고, 동쪽을 바라보는 방향이 3인 형태 시작 좌표는 1,1
        // 주사위의 이동 방향은 동쪽

        int d = 0; // 처음에는 동쪽으로 간다
        Pair start = new Pair(0, 0); // 시작점은 0,0이다
        int drow = 0;
        int dcol = 0;
        for (int i = 0; i < K; i++) {
            drow = start.row + dr[d];
            dcol = start.col + dc[d];

            // 1. 주사위가 이동 방향으로 한 칸 굴러 간다 만약 이동 방향에 칸이 없다면 이동 방향을 반대로 한 다음 한 칸 굴러간다
            if (drow < 0 || drow >= N || dcol < 0 || dcol >= M) {
                d += 2; // 방향을 반대로 바꾼다
                d %= 4;

                drow = start.row + dr[d];
                dcol = start.col + dc[d];
            }

            //2. bfs
            int B = map[drow][dcol];
            int C = bfs(new Pair(drow, dcol));
            answer = answer + B * C;

            // 3. 주사위의 아랫면에 있는 정수 A와 주사위 칸 (x,y)에 있는 정수를 비교해 이동 방향을 정한다
            setDice(d);
            int A = dice[0];

            if (A > B) {
                d = (d + 1) % 4;
            } else if (A < B) {
                d = (d - 1) < 0 ? d - 1 + 4 : d - 1;
            }

            start.row = drow;
            start.col = dcol;
        }

        System.out.println(answer);

    }

    private static int bfs(Pair pair) {
        int cnt = 0;
        boolean[][] v = new boolean[N][M];
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(pair);
        v[pair.row][pair.col] = true;

        Pair cur;
        int drow;
        int dcol;
        while (!queue.isEmpty()) {
            cur = queue.poll();

            cnt++;

            for (int d = 0 ; d < 4; d++){
                drow = cur.row + dr[d];
                dcol = cur.col + dc[d];

                if (drow < 0 || drow >= N || dcol < 0 || dcol >= M) continue;
                if (v[drow][dcol] || map[cur.row][cur.col] != map[drow][dcol]) continue;
                v[drow][dcol] = true;

                queue.offer(new Pair(drow, dcol));
            }
        }

        return cnt;
    }

    static class Pair {
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    //주사위 변경하는 매서드
    static public void setDice(int d) {

        int temp = dice[0]; // 아래
        // 0 : 아래
        // 1 : 동
        // 2 : 남
        // 3 : 서
        // 4 : 북
        // 5: 위
        if (d == 0) { // 동쪽으로 갈 때
            dice[0] = dice[1]; // 아래 = 동
            dice[1] = dice[5]; // 동 = 위
            dice[5] = dice[3]; // 위 = 서
            dice[3] = temp; // 서 = 아래
        } else if (d == 1) { //남쪽으로 갈 떄
            dice[0] = dice[2];// 아래 = 남
            dice[2] = dice[5];// 남 = 위
            dice[5] = dice[4];// 위 = 북
            dice[4] = temp;// 북 = 아래
        } else if (d == 2) {
            dice[0] = dice[3];// 아래 = 서
            dice[3] = dice[5];// 서 = 위
            dice[5] = dice[1];// 위 = 동
            dice[1] = temp;// 동 = 아래
        } else {
            dice[0] = dice[4];//아래 = 북
            dice[4] = dice[5];// 북 = 위
            dice[5] = dice[2];// 위 = 남
            dice[2] = temp;// 남 = 아래
        }

    }
}
