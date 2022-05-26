package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BOJ_우주신과의교감_HoonyCode {

    static int N, M;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] in = br.readLine().split(" ");

        N = Integer.parseInt(in[0]);
        M = Integer.parseInt(in[1]);

        initParents(); // 부모 초기화

        Data[] god = new Data[N + 1];


        for (int i = 1; i <= N; i++) {
            in = br.readLine().split(" ");
            god[i] = new Data(Double.parseDouble(in[0]), Double.parseDouble(in[1]));
        }

        //data 생성
        List<God> list = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                double len = Math.sqrt(Math.pow(god[i].X - god[j].X, 2) + Math.pow(god[i].Y - god[j].Y, 2));
                list.add(new God(i, j, len));
            }
        }


        Collections.sort(list, (o1, o2) -> Double.compare(o1.len, o2.len));


        for (int j = 0; j < M; j++) {
            in = br.readLine().split(" ");
            union(Integer.parseInt(in[0]), Integer.parseInt(in[1]));
        }

        double answer = 0;
        int cnt = M;

        for (God cur : list) {

            if (union(cur.A, cur.B)) {
                answer += cur.len;
                cnt++;
                if (cnt == N - 1) break;
            }
        }

        System.out.printf("%.2f", answer);
    }

    private static class God {
        int A, B;
        double len;

        public God(int a, int b, double len) {
            A = a;
            B = b;
            this.len = len;
        }
    }

    static private boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) {
            return false;
        } else {
            parents[y] = x;
            return true;
        }
    }

    static private int find(int x) {
        if (parents[x] != x) {
            return parents[x] = find(parents[x]);
        }
        return x;
    }

    private static void initParents() {
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }

    static class Data {
        double X, Y;

        public Data(double x, double y) {
            X = x;
            Y = y;
        }
    }

}
