import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ17435 {

    static int[] fn;
    static int[][] sparseTable;
    static int logn = 20;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        m = Integer.parseInt(br.readLine());

        fn = new int[m + 1];
        sparseTable = new int[logn][m + 1];

//      f(1) f(2) f(3) ... f(n);
        String[] in = br.readLine().split(" ");
        for (int i = 1; i <= m; i++){
            fn[i] = Integer.parseInt(in[i-1]);
        }

        makeSparseTable();

        //쿼리 개수 Q가 주어진다.
        int Q = Integer.parseInt(br.readLine());

        //fn(x) 가 주어진다.
        // n과 x가 주어진다.
        StringBuilder sb = new StringBuilder();

        int n, x;
        for (int i = 0; i < Q; i++) {
            in = br.readLine().split(" ");
            n = Integer.parseInt(in[0]);
            x = Integer.parseInt(in[1]);
            sb.append(query(n, x)).append('\n');
        }
        System.out.print(sb.toString());
    }

    // v 정점에서 출발하여 n개의 간선을 이동하여 도착한 정점 찾기
    public static int query(int n, int v) {

        for (int bit = 19; bit >= 0; bit--) {
            if ((n & (1 << bit)) != 0){
                v = sparseTable[bit][v];
            }
        }
        return v;
    }

    public static void makeSparseTable() {
        // 1번 이동해서 도착하는 정점
        for (int i = 1; i <= m; i++) {
            sparseTable[0][i] = fn[i];
        }

        //log n번 까지 이동해서 도착하는 정점
        for (int k = 1; k < logn; k++) {
            for (int i = 1; i <= m; i++) {
                int next = sparseTable[k - 1][i];
                sparseTable[k][i] = sparseTable[k - 1][next];
            }
        }
    }
}
