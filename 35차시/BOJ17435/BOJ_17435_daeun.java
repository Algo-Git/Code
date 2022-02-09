import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17435_합성함수와쿼리 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int m = Integer.parseInt(br.readLine());
        int[] f = new int[m + 1];
        int[][] arr = new int[20][m + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            f[i] = Integer.parseInt(st.nextToken());
            arr[0][i] = f[i];
        }

        // 행 : 0 ~ 19 (최대 2^19 == 524,288)
        // 열 : 이동 번호만큼(1 ~ m)
        for (int i = 1; i < 20; i++) {
            for (int j = 1; j <= m; j++) {
                arr[i][j] = arr[i - 1][arr[i - 1][j]];
            }
        }

        int Q = Integer.parseInt(br.readLine());    // 쿼리의 개수
        int n, x;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());

            // 만약 n = 6 이면, 2 + 4 => 마지막인 4의 이동번호가 답이 되어야하므로 2진수로 변환
            String s = Integer.toBinaryString(n);   // 2진수로 변환
            int res = 0;
            int idx = x;    // 바꿀 이동번호 (인덱스)
            for (int j = s.length() - 1; j >= 0; j--) {
                if (s.charAt(j) == '0') {
                    continue;
                } else {
                    res += (int) Math.pow(2, s.length() - 1 - j);   // 6을 만들기 위해 +2, +4
                    if (res == n) { // 6을 찾으면 출력
                        sb.append(arr[s.length() - 1 - j][idx]).append("\n");
                        break;
                    }
                    idx = arr[s.length() - 1 - j][idx]; // 이동 번호 변경
                }

            }
        }

        System.out.print(sb);
    }
}