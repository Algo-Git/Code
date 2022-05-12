package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.*;

public class BOJ_8911_HoonyCode {

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();


        for (int tc = 0; tc < T; tc++) {
            char[] move = br.readLine().toCharArray();
            int row = 0;
            int col = 0;
            int dir = 0;

            int maxRow = 0;
            int minRow = 0;
            int maxCol = 0;
            int minCol = 0;


            for (int i = 0; i < move.length; i++) {
                char in = move[i];

                if (in == 'F') {
                    row += dr[dir];
                    col += dc[dir];
                } else if (in == 'B') {
                    row -= dr[dir];
                    col -= dc[dir];
                } else if (in == 'R') {
                    dir = (dir + 1) % 4;
                    continue;
                } else {
                    dir = (dir + 4 - 1) % 4;
                    continue;
                }

                maxRow = max(maxRow, row);
                minRow = min(minRow, row);
                maxCol = max(maxCol, col);
                minCol = min(minCol, col);
            }

            int answer = (maxRow - minRow) * (maxCol - minCol);

            sb.append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static class Pair {
        int row, col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

}
