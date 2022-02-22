import javax.management.StringValueExp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 인구 이동

public class BOJ16234_HoonyCode {

    static int N, L, R;
    static int[][] map;
    static int[][] subMap;
    static boolean[][] v;
    static boolean flag;
    static int answer = 0;

    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        N = Integer.parseInt(in[0]);
        L = Integer.parseInt(in[1]);
        R = Integer.parseInt(in[2]);
        map = new int[N][N];
        subMap = new int[N][N];
        v = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(in[j]);
        }

        while (true) {
            flag = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (v[i][j]) continue;
                    bfs(new int[]{i, j});
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    v[i][j] = false;
                    map[i][j] = subMap[i][j];
                }
            }
            if (!flag)
                break;

            answer++;
        }


        System.out.println(answer);


    }

    private static void bfs(int[] p) {
        v[p[0]][p[1]] = true;
        ArrayList<int[]> list = new ArrayList<>();
        Queue<int[]> que = new LinkedList<>();
        que.offer(p);

        int[] cur;
        int row;
        int col;
        int sum = 0;
        while (!que.isEmpty()) {
            cur = que.poll();
            list.add(cur);
            sum += map[cur[0]][cur[1]];

            for (int d = 0; d < 4; d++) {
                row = cur[0] + dr[d];
                col = cur[1] + dc[d];

                if (row < 0 || row >= N || col < 0 || col >= N) continue;
                if (v[row][col]) continue;
                int sub = Math.abs(map[cur[0]][cur[1]] - map[row][col]);
                if (sub < L || sub > R) continue;
                v[row][col] = true;
                que.offer(new int[]{row, col});
            }
        }

        sum /= list.size();

        for (int[] point : list)
            subMap[point[0]][point[1]] = sum;

        if (list.size() > 1) flag = true;
    }
}
