import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SOF_사물인식최소면적산출프로그램 {

    static int N, K;
    static List<Pair>[] list;
    static int res;

    public static void main(String[] args) throws IOException {
        //사물 중 넓이가 가장 작은 것을 찾아서 그 넓이를 출력하는 프로그램
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]); // 점의 개수
        K = Integer.parseInt(input[1]); // 가질수 있는 색깔의 총 개수

        list = new List[K+1];

        for (int i = 1; i <= K; i++) {
            list[i] = new ArrayList<>();
        }

        int x, y, index;
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            x = Integer.parseInt(input[0]);
            y = Integer.parseInt(input[1]);
            index = Integer.parseInt(input[2]);
            list[index].add(new Pair(x, y));
        }

        res = Integer.MAX_VALUE;
        dfs(1, -1000, -1000, 1000, 1000);

        System.out.println(res);

    }

    private static void dfs(int dept, int Max_x, int Max_y, int Min_x, int Min_y) {

        int size = (Max_x - Min_x) * (Max_y - Min_y);
        if (res <= size)
            return;

        if (dept == K + 1) {
            res = size;
            return;
        }

        for (Pair cur : list[dept]) {
            dfs(dept + 1,
                    Math.max(Max_x, cur.x),
                    Math.max(Max_y, cur.y),
                    Math.min(Min_x, cur.x),
                    Math.min(Min_y, cur.y));
        }


    }

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
