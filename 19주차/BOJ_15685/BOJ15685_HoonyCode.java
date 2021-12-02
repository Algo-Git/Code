import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ15685 {

    static int N; // 드래곤 커브의 개수
    static boolean[][] map = new boolean[101][101];
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        String[] input;
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            dragonCurve(Integer.parseInt(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2]), Integer.parseInt(input[3]));
        }

        for (int i = 0 ; i < 100 ; i++){
            for (int j = 0 ; j < 100; j++){
                if (map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1]){
                    res++;
                }
            }
        }

        System.out.println(res);

    }

    static void dragonCurve(int x, int y, int d, int g) {
        ArrayList<Integer> dirList = new ArrayList<>();
        dirList.add(d);

        for (int i = 1; i <= g; i++) {
            for (int j = dirList.size() - 1; j >= 0; j--) {
                dirList.add((dirList.get(j) + 1) % 4);
            }
        }

        map[y][x] = true;
        for (Integer dir : dirList) {
            x += dx[dir];
            y += dy[dir];
            map[y][x] = true;
        }

    }
}
