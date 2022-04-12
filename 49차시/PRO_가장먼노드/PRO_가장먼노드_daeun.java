import java.util.*;

public class PRO_49189_가장먼노드 {
    public static void main(String[] args) {

        System.out.print(Solution.solution(
                6, new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}}
        ));

    }

    static class Solution {
        public static int solution(int n, int[][] edge) {
            int answer = 0;

            // 인접 리스트 생성
            ArrayList<Integer>[] list = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                list[i] = new ArrayList<>();
            }
            for (int[] e : edge) {
                list[e[0]].add(e[1]);
                list[e[1]].add(e[0]);
            }

            // 큐, 방문체크 배열, 노드별 깊이 저장 배열
            Queue<Data> q = new LinkedList<>();
            boolean[] v = new boolean[n + 1];
            int[] cnt = new int[n + 1];

            // 1번 노드를 큐에 추가, 방문체크
            q.offer(new Data(0, 1));
            v[1] = true;

            int max = 0;    // 최대 깊이 저장 변수
            while (!q.isEmpty()) {
                Data here = q.poll();

                max = Math.max(max, here.depth);    // 최대 깊이 업데이트

                // 방문 안한 인접 노드이면 큐에 추가, 방문체크, 깊이 저장
                for (int data : list[here.num]) {   
                    if (v[data]) continue;
                    q.offer(new Data(here.depth + 1, data));
                    v[data] = true;
                    cnt[data] = here.depth + 1;
                }
            }

            // 최대 깊이를 가진 노드가 있으면 answer++
            for(int temp : cnt){
                if(temp == max) answer++;
            }

            return answer;
        }
    }

    static class Data {
        int depth, num;

        public Data(int depth, int num) {
            this.depth = depth;
            this.num = num;
        }
    }
}