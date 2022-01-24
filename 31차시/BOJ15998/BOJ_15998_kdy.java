import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N15998 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Long pre = 0L;
        Long min = 0L;
        Long minB = Long.MAX_VALUE;//잔액
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Long a = Long.parseLong(st.nextToken());
            Long b = Long.parseLong(st.nextToken());
            if (min == Long.MIN_VALUE) continue;
            if (pre + a < 0) {
                if (min == 0) {
                    min = b - a - pre;
                }
                if (b != 0 && b < minB) minB = b;
                if (b - a - pre > min && b > min) {
                    min = Long.MIN_VALUE;
                } else {
                    min = GCD(min, b - a - pre);
                    if (min <= minB && minB != Long.MAX_VALUE) {
                        min = Long.MIN_VALUE;
                    }
                }
            } else {
                if (pre + a != b) min = Long.MIN_VALUE;
            }
            pre = b;
        }
        if (min == Long.MIN_VALUE) {
            System.out.println(-1);
        } else if (min == 0L) System.out.println(1);
        else System.out.println(min);
    }

    private static Long GCD(Long a, long b) {
        while (b > 0) {
            long tmp = a;
            a = b;
            b = tmp % b;
        }
        return a;
    }
}
