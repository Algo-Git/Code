import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ24270_HoonyCode {

    private static final int div = 1_000_000_007;
    static int N, K; // 미니 버킷 리스트에 적힌 일의 수 N 과 2022년이 나뉜 단위시간의 개수 K가 주어지낟.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        N = Integer.parseInt(in[0]);
        K = Integer.parseInt(in[1]);

        in = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            K -= Integer.parseInt(in[i]);
        }

        long answer = 1;

        for (int i = 0 ; i < N ; i++){
            K += 1;
            answer = (answer * K) % div;
        }

        System.out.println(answer);

    }
}
