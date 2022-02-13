import java.io.*;
import java.util.*;


public class BOJ12875 {

    static int n;
    static int d;
    static int[][] map;
    private static final int INF = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        d = Integer.parseInt(br.readLine());

        map = new int[n][n];

        char[] in;
        for (int i = 0; i < n; i++) {
            in = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                if (in[j] == 'N')
                    map[i][j] = INF;
                else
                    map[i][j] = 1;
            }
        }

        for (int i = 0 ; i < n ; i++) map[i][i] = 0;

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer = Math.max(answer, map[i][j]);
            }
        }

        if (answer == INF) System.out.println(-1);
        else System.out.println(answer * d);


    }

}