import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Math.*;

public class BOJ9251 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] s1 = br.readLine().toCharArray();
        char[] s2 = br.readLine().toCharArray();

        int n = s1.length;
        int m = s2.length;

        int[][] lcs = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                lcs[i][j] = max(lcs[i - 1][j], lcs[i][j - 1]);
                if (s1[i - 1] == s2[j - 1])
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
            }
        }
        System.out.println(lcs[n][m]);
    }
}
