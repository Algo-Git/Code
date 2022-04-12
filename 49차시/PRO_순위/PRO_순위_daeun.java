public class PRO_49191_순위 {
    public static void main(String[] args) {
        System.out.print(solution(5, new int[][]{{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}}));
    }

    static boolean[][] highs, lows;

    public static int solution(int n, int[][] results) {
        int answer = 0;

        // 조상 노드, 자식 노드 구하기
        highs = new boolean[n + 1][n + 1];  // 조상 노드 체크
        lows = new boolean[n + 1][n + 1];   // 자식 노드 체크
        for (int[] result : results){
            int high = result[0];
            int low = result[1];
            highs[low][high] = true;
            lows[high][low] = true;
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++) {
                if(highs[i][j]) // 조상 노드의 모든 조상 체크
                    dfs(i, j, n);
                if(lows[i][j])  // 자식 노드의 모든 자식 체크
                    dfs2(i, j, n);
            }
        }

        for(int i = 1; i <= n; i++){
            boolean check = false;
            for (int j = 1; j <= n; j++){
                if(i == j) continue;    // 자기 자신의 인덱스이면 continue
                if(!highs[i][j] && !lows[i][j]){    // 조상 노드, 자식 노드 둘다 없으면 순위 못 매김
                    check = true;
                    break;
                }
            }
            if(!check) answer++;    // 순위를 매길 수 있으면 answer + 1
        }

        return answer;
    }

    private static void dfs(int start, int end, int n) {    // 조상 노드의 모든 조상 구하기
        for(int i = 1; i <= n; i++){
            if(!highs[end][i] || highs[start][i]) continue;
            highs[start][i] = true;
            dfs(start, i, n);
        }
    }

    private static void dfs2(int start, int end, int n) {   // 자식 노드의 모든 자식 구하기
        for(int i = 1; i <= n; i++){
            if(!lows[end][i] || lows[start][i]) continue;
            lows[start][i] = true;
            dfs2(start, i, n);
        }
    }
}