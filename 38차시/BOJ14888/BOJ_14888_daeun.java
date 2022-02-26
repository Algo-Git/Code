import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14888_연산자끼워넣기 {

    static int N, nums[], opCnt[];
    static int min, max;
    static int[] op;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());    // 수의 개수
        nums = new int[N];
        opCnt = new int[4];  // 덧셈, 뺄셈, 곱셈, 나눗셈의 개수

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            opCnt[i] = Integer.parseInt(st.nextToken());
        }

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        op = new int[N - 1];

        perm(0);

        System.out.println(max);
        System.out.print(min);
    }

    private static void perm(int cnt) {
        if (cnt == N - 1) { // 연산자 N-1개 만큼 고르면 return
            int res = nums[0];
            for (int i = 0; i < N - 1; i++) {
                if (op[i] == 0) { // 덧셈
                    res += nums[i + 1];
                } else if (op[i] == 1) {   // 뺄셈
                    res -= nums[i + 1];
                } else if (op[i] == 2) {   // 곱셈
                    res *= nums[i + 1];
                } else {  // 나눗셈
                    res /= nums[i + 1];
                }
            }
            max = Math.max(max, res);
            min = Math.min(min, res);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (opCnt[i] == 0) continue;    // 연산자가 0개이면 continue
            opCnt[i]--; // 연산자 개수 빼기
            op[cnt] = i;    // 연산자 번호 넣기(덧셈:0, 뺄셈:1, 곱셈:2, 나눗셈:3)
            perm(cnt + 1);
            opCnt[i]++; // 연산자 개수 다시 더하기
        }
    }
}