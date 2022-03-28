public class PRO_43162_네트워크 {
    public static void main(String[] args) {

        System.out.print(Solution.solution(3, new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));

    }

    static class Solution {

        static boolean[] v;

        public static int solution(int n, int[][] computers) {
            int answer = 0;

            v = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (v[i]) continue;
                v[i] = true;
                dfs(n, i, computers);
                answer++;
            }

            return answer;
        }

        private static void dfs(int n, int idx, int[][] computers) {
            for (int i = 0; i < n; i++) {
                if (computers[idx][i] == 0) continue;
                if (v[i]) continue;
                v[i] = true;
                dfs(n, i, computers);
            }
        }
    }
}