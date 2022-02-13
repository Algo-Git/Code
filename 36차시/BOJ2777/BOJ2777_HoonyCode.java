import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2777 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int n;
        for (int i = 0 ; i < T ; i++){
            n = Integer.parseInt(br.readLine());
            sb.append(solve(n)).append('\n');
        }
        System.out.print(sb.toString());
    }

    public static int solve(int n){

        if (n == 1) return 1;

        int answer = 0;

        for (int i = 9 ; i > 1 ; i --) {

            while (n % i == 0) {
                n /= i;
                answer++;
            }
        }
        return n == 1 ? answer : -1;
    }

}
