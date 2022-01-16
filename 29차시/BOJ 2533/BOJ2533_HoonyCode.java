import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class BOJ2533 {

    static int N;
    /**
     * 얼리 아답터가 아닌 사람들은 자신의 모든 친구들이 얼리 아답터일 때만 이 아이디어를 받아들인다.
     * 모든 두 정점 사이에 이들을 잇는 경로가 존재하면서 사이클이 존재하지 않는 경우만 고려한다.
     * 출력 : 주어진 친구 관계 그래프에서 아이디어를 전파하는데 필요한 얼리 아답터의 최소 수를 하나의 정수로 출력한다.
     */


    static List<Integer>[] list;
    static int[][] dp;
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        list = new List[N + 1];
        dp = new int[N + 1][2];
        visited = new boolean[N + 1];


        for (int i = 1; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        int A, B;
        for (int i = 0; i < N - 1; i++) {
            String[] input = br.readLine().split(" ");
            A = Integer.parseInt(input[0]);
            B = Integer.parseInt(input[1]);
            list[A].add(B);
            list[B].add(A);
        }

        find(1); // 출발지
        System.out.println(min(dp[1][0], dp[1][1]));
    }

    private static void find(int start) {
        visited[start] = true;
        dp[start][0] = 1;
        for (int child : list[start]){
            if (visited[child]) continue;

            find(child);
            dp[start][1] += dp[child][0];
            dp[start][0] += min(dp[child][1], dp[child][0]);
        }
    }
}
