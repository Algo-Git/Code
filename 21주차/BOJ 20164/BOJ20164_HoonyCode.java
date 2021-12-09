import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ20164_HoonyCode {

    static int Min = Integer.MAX_VALUE;
    static int Max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 1 <= N <= 10^9 -1;

        dfs(N, OddSum(N));

        StringBuilder sb = new StringBuilder();
        sb.append(Min).append(' ').append(Max);
        System.out.print(sb.toString());

    }

    private static void dfs(int N, int oddCnt) {
        if (N < 10) {
            Max = Math.max(Max, oddCnt);
            Min = Math.min(Min, oddCnt);
            return;
        }

        int num1, num2, num3;
        if (N >= 100) { // 100의 자리 수라면
            for (int i = 10; i <= N / 10; i = i * 10) {
                num1 = N % i;
                for (int j = 10; j <= N / i; j = j * 10) {
                    num2 = N / i % j;
                    num3 = N / i / j;
                    dfs(num1 + num2 + num3, oddCnt + OddSum(num1 + num2 + num3));
                }
            }
        } else { // 십의 자리수 라면
            num1 = N / 10; // 십의 자리수
            num2 = N % 10; // 일의 자리수
            dfs(num1 + num2, oddCnt + OddSum(num1 + num2));
        }
    }

    // 홀수의 개수를 구하는 메서드
    private static int OddSum(int N) {
        int res = 0;
        while (N > 0) {
            if (N % 10 % 2 == 1) res++;
            N = N / 10;
        }
        return res;
    }
}
