import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_23288_주사위굴리기2 {

    static int N, M, K, map[][], dice[][], direct;
    static int[] dr = {0, 1, 0, -1};    // 동남서북
    static int[] dc = {1, 0, -1, 0};
    static boolean[][] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        K = Integer.parseInt(s[2]);
        map = new int[N + 1][M + 1];
        dice = new int[4][3];
        v = new boolean[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            s = br.readLine().split(" ");
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(s[j - 1]);
            }
        }
        dice[0][1] = 2;
        dice[1][0] = 4;
        dice[1][1] = 1;
        dice[1][2] = 3;
        dice[2][1] = 5;
        dice[3][1] = 6;

        direct = 0; // 동 0 남 1 서 2 북 3
        int r = 1, c = 1;
        int total = 0;
        for (int i = 0; i < K; i++) {
            int nr = r + dr[direct];
            int nc = c + dc[direct];
            if(nr < 1 || nr > N || nc < 1 || nc > M){   // 이동방향에 칸이 없다면 방향 반대로
                if((direct & 1) == 0) direct = Math.abs(direct - 2);    // 짝수
                else direct = (direct + 2) % 4;
            }
            r += dr[direct];
            c += dc[direct];

            // 전개도 변경
            changeMap(direct);
            // 다음 방향 구하기
            changeDirect(r, c);
            // 점수 구하기
            v = new boolean[N + 1][M + 1];
            v[r][c] = true;
            total += (dfs(r, c, map[r][c]) + 1) * map[r][c];
        }

        System.out.print(total);
    }

    private static int dfs(int r, int c, int num) {
        int sum = 0;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr < 1 || nr > N || nc < 1 || nc > M || v[nr][nc]) continue;
            v[nr][nc] = true;
            if (map[nr][nc] == num) {
                sum += dfs(nr, nc, num) + 1;
            }
        }
        return sum;
    }

    private static void changeDirect(int r, int c) {
        int A = dice[3][1]; // 주사위 아랫면에 있는 정수 A
        int B = map[r][c];  // (r,c)에 있는 정수 B
        if (A > B) direct = (direct + 1) % 4;
        else if (A < B) direct = (direct - 1 + 4) % 4;
    }

    private static void changeMap(int d) {
        int temp;
        if (d == 0) { // 동
            temp = dice[1][2];  // 아랫면에 넣을 숫자
            dice[1][2] = dice[1][1];
            dice[1][1] = dice[1][0];
            dice[1][0] = dice[3][1];
        } else if (d == 1) {   // 남
            temp = dice[2][1];  // 아랫면에 넣을 숫자
            dice[2][1] = dice[1][1];
            dice[1][1] = dice[0][1];
            dice[0][1] = dice[3][1];
        } else if (d == 2) {   // 서
            temp = dice[1][0];  // 아랫면에 넣을 숫자
            dice[1][0] = dice[1][1];
            dice[1][1] = dice[1][2];
            dice[1][2] = dice[3][1];
        } else {  // 북
            temp = dice[0][1];  // 아랫면에 넣을 숫자
            dice[0][1] = dice[1][1];
            dice[1][1] = dice[2][1];
            dice[2][1] = dice[3][1];
        }
        dice[3][1] = temp;
    }
}
