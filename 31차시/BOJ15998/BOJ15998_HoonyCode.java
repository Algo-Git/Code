import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2022-01-22 by 김영훈
 * <p>
 * 문제이름 : 카카오머니
 * 설명 : 최소 충전 단위 구하기
 */

public class BOJ15998 {

    static int n;
    static long m = 0;
    static long[] a, b;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        a = new long[n + 1];
        b = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            String[] in = br.readLine().split(" ");
            a[i] = Long.parseLong(in[0]);
            b[i] = Long.parseLong(in[1]);
            m = gcd(m, b[i] - b[i - 1] - a[i]); // 최대공약수 알고리즘
        }

        for (int i = 1; i <= n; i++) {
            if (b[i] - b[i - 1] == a[i]) continue;
            if (a[i] > 0
                    || (m > 0 && m <= b[i])
                    || -a[i] < b[i - 1]) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(m > 0 ? m : 1); // m == 0 이면 임금만 한 상황
    }

    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }

        return Math.abs(a);
    }
}
