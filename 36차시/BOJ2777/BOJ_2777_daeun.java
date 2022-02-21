import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_2777_숫자놀이 {

    static int N;
    static int res;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            res = -1;

            if (N == 1) res = 1;    // 1일 때 solve 함수에서는 cnt = 0이 되므로 미리 처리
            else solve();

            sb.append(res).append("\n");
        }

        System.out.print(sb);
    }

    static void solve() {

        int temp = N;
        int cnt = 0;

        while (true) {
            if (temp == 1) {    // 1 까지 왔다는 것은 모든 자리수를 구했다는 뜻
                res = cnt;
                break;
            }

            boolean check = false;
            for (int i = 9; i > 1; i--) {   // 9부터 나눠야 자리수가 구해짐
                if (temp % i == 0) {    // 9~2 로 나누어지면 나눈 몫을 다시 temp에 넣고 자리수 + 1
                    temp = temp / i;
                    cnt++;
                    check = true;
                    break;
                }
            }

            if (!check) break;  // 9~2 안나눠지면 소수 => 소수는 무조건 -1
        }
    }
}