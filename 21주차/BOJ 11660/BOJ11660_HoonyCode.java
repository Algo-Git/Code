import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11660_HoonyCode {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        int[][] map = new int[N+1][N+1];
        for(int i = 1 ; i <= N ; i++){
            input = br.readLine().split(" ");
            for(int j = 1; j <=N ; j++){
                map[i][j] = map[i-1][j] + map[i][j-1] + Integer.parseInt(input[j-1]) - map[i-1][j-1];
            }
        }
        for(int i = 0 ; i < M ; i++){
            input = br.readLine().split(" ");
            int x1 = Integer.parseInt(input[0]);
            int y1 = Integer.parseInt(input[1]);
            int x2 = Integer.parseInt(input[2]);
            int y2 = Integer.parseInt(input[3]);

            int res = map[x2][y2] - map[x1-1][y2] - map[x2][y1-1] + map[x1-1][y1-1];
            sb.append(res).append('\n');
        }

        System.out.print(sb.toString());
    }
}