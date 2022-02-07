import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1198_삼각형으로자르기 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());    // 다각형의 점 개수
        int[][] a = new int[N][2];    // 좌표

        String[] s;
        for (int i = 0; i < N; i++) {
            s = br.readLine().split(" ");
            a[i][0] = Integer.parseInt(s[0]);
            a[i][1] = Integer.parseInt(s[1]);
        }

        // 점 3개씩 골라서 최대 넓이 구하기
        double res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    double tmp = 0.5 * Math.abs(a[i][0] * a[j][1] + a[j][0] * a[k][1] + a[k][0] * a[i][1]
                            - (a[j][0] * a[i][1] + a[k][0] * a[j][1] + a[i][0] * a[k][1]));
                    res = Math.max(res, tmp);
                }
            }
        }

        System.out.printf("%.1f", res);
    }
}