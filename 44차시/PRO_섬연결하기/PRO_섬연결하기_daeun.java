import java.util.*;

public class PRO_42861_섬연결하기 {
    public static void main(String[] args) {
//        System.out.print(Solution.solution(4, new int[][]{{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}}));
        System.out.print(Solution.solution(5, new int[][]{{0, 1, 1}, {3, 1, 1}, {0, 2, 2}, {0, 3, 2}, {0, 4, 100}}));
    }

    static class Solution {

        static int N, answer;
        static int[] parents;

        static void make() {
            parents = new int[N];
            for (int i = 0; i < N; i++) {
                parents[i] = i;
            }
        }

        static int find(int a) {
            if (a == parents[a]) return a;
            return parents[a] = find(parents[a]);
        }

        static boolean union(int a, int b) {
            int aRoot = find(a);
            int bRoot = find(b);
            if (aRoot == bRoot) return false;

            // a의 부모와 b의 부모 중, 더 작은 번호를 가진쪽이 부모가 됨
            if (aRoot < bRoot) {
                for (int i = 0; i < N; i++){    // b의 부모를 a의 부모로 모두 변경
                    if(parents[i] == bRoot)
                        parents[i] = aRoot;
                }
            }
            else {
                for (int i = 0; i < N; i++){    // a의 부모를 b의 부모로 모두 변경
                    if(parents[i] == aRoot)
                        parents[i] = bRoot;
                }
            }
            return true;
        }

        public static int solution(int n, int[][] costs) {
            N = n;

            Arrays.sort(costs, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return Integer.compare(o1[2], o2[2]);
                }
            });

            make();

            int cnt = 0;
            for (int i = 0; i < costs.length; i++) {
                int a = costs[i][0];
                int b = costs[i][1];
                if (union(a, b)) {  // 부모가 바꼈다면 새로운 간선이 추가된 것
                    answer += costs[i][2];
                    cnt++;  // 간선 개수 + 1
                }
                if (cnt == n - 1) break;    // 간선 개수가 n - 1이 되면 끝났으므로 break
            }

            return answer;
        }
    }
}