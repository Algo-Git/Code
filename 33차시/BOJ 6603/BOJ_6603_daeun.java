import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6603_로또 {

    static int k;
    static int[] S, numbers;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        numbers = new int[6];

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());

            if (k == 0) break;

            S = new int[k];
            for (int i = 0; i < k; i++) {
                S[i] = Integer.parseInt(st.nextToken());
            }

            comb(0, 0);

            sb.append("\n");
        }

        System.out.print(sb);
    }

    static void comb(int start, int cnt) {
        if (cnt == 6) {
            for (int i = 0; i < 6; i++) {
                sb.append(numbers[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < k; i++) {
            numbers[cnt] = S[i];
            comb(i + 1, cnt + 1);
        }
    }
}