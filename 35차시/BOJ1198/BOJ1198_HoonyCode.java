import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Math.*;

public class BOJ1198 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int x[] = new int[N];
        int y[] = new int[N];


        String[] in;
        for (int i = 0; i < N; i++) {
            in = br.readLine().split(" ");
            x[i] = Integer.parseInt(in[0]);
            y[i] = Integer.parseInt(in[1]);
        }


        double answer = -1;
        double area;
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                for (int k = j + 1; k < N; k++) {
                    answer = max(answer,0.5 * abs(x[i] * y[j] + x[j] * y[k] + x[k] * y[i] - x[j] * y[i] - x[k] * y[j] - x[i] * y[k]));
                }
            }
        }

        System.out.printf("%.1f", answer);

    }

}
