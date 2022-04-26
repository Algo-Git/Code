import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17779_게리맨더링2 {

    static int map[][], totalCnt, N, answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                totalCnt += map[i][j];
            }
        }

        answer = Integer.MAX_VALUE;
        for (int x = 1; x < N - 1; x++) {
            for (int y = 1; y < N - 1; y++) {
                for (int d1 = 1; d1 < N - 1; d1++) {
                    for (int d2 = 1; d2 < N - 1; d2++) {
                        if (x + d1 + d2 >= N || y - d1 < 0 || y + d2 >= N)
                            continue;
                        solve(x, y, d1, d2);
                    }
                }
            }
        }

        System.out.print(answer);
    }

    private static void solve(int x, int y, int d1, int d2) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int total = 0;

        // 1번 선거구
        int sum = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j <= y; j++) {
                sum += map[i][j];
            }
        }
        int temp = 1;
        for (int i = x; i < x + d1; i++, temp++) {
            for (int j = 0; j <= y - temp; j++) {
                sum += map[i][j];
            }
        }
        max = Math.max(max, sum);
        min = Math.min(min, sum);
        total += sum;

        // 2번 선거구
        sum = 0;
        for (int i = 0; i < x; i++) {
            for (int j = y + 1; j < N; j++){
                sum += map[i][j];
            }
        }
        temp = 1;
        for (int i = x; i <= x + d2; i++, temp++) {
            for (int j = y + temp; j < N; j++){
                sum += map[i][j];
            }
        }
        max = Math.max(max, sum);
        min = Math.min(min, sum);
        total += sum;

        // 3번 선거구
        sum = 0;
        temp = 0;
        for(int i = x + d1; i <= x + d1 + d2; i++, temp++){
            for(int j = 0; j < y - d1 + temp; j++){
                sum += map[i][j];
            }
        }
        for(int i = x + d1 + d2 + 1; i < N; i++){
            for(int j = 0; j < y - d1 + d2; j++){
                sum += map[i][j];
            }
        }
        max = Math.max(max, sum);
        min = Math.min(min, sum);
        total += sum;

        // 4번 선거구
        sum = 0;
        temp = 0;
        for(int i = x + d2 + 1; i <= x + d1 + d2; i++, temp++){
            for(int j = y + d2 - temp; j < N; j++){
                sum += map[i][j];
            }
        }
        for(int i = x + d1 + d2 + 1; i < N; i++){
            for(int j = y + d2 - d1; j < N; j++){
                sum += map[i][j];
            }
        }
        max = Math.max(max, sum);
        min = Math.min(min, sum);
        total += sum;

        // 5번 선거구
        sum = totalCnt - total;
        max = Math.max(max, sum);
        min = Math.min(min, sum);

        answer = Math.min(answer, max - min);
    }
}