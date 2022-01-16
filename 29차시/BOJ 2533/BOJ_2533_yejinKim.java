// [골드3] 사회망 서비스 (SNS)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 트리 + DP
// 재귀 + DP
public class BOJ_2533_yejinKim {
    static int N;
    static ArrayList<Integer>[] relations;
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        relations = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            relations[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            relations[from].add(to);
            relations[to].add(from);
        }

        // 루트 노드가 0번이라고 가정하고 푼다. 무엇이든 상관 없다.
        dp = new int[N][2]; // 0번은 내가 얼리어답터인 경우, 1번은 얼리어답터가 아닌 경우 얼리어답터 수
        visited = new boolean[N];
        visited[0] = true;
        find(0);
        System.out.println(Math.min(dp[0][0], dp[0][1]));
    }

    private static void find(int person) {
        // 확인할 친구가 없는 경우를 위해 초기화
        dp[person][0] = 1;
        dp[person][1] = 0;

        for (int friend : relations[person]) {
            if (visited[friend]) continue;
            visited[friend] = true;
            find(friend);
            dp[person][0] += Math.min(dp[friend][0], dp[friend][1]); // 친구가 얼리어답터든 아니든 상관없음
            dp[person][1] += dp[friend][0]; // 친구는 얼리어답터여야 함.
        }
    }
}
