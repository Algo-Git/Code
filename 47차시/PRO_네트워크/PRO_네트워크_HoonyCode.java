package PROGRAMMERS;

import java.util.LinkedList;
import java.util.Queue;

public class PRO_네트워크_HoonyCode {

    class Solution {

        boolean[] v;

        public int solution(int n, int[][] computers) {
            int answer = 0;

            //bfs로 그 점이랑 연결되어 있는 부분을 찾아 내자
            v = new boolean[n];

            for (int i = 0; i < n; i++) {
                if (v[i]) continue;
                answer++;
                bfs(i, computers, n);
            }

            return answer;
        }


        public void bfs(int start, int[][] computers, int size) {
            Queue<Integer> que = new LinkedList<>();
            que.offer(start);
            v[start] = true;


            int cur;
            while (!que.isEmpty()) {
                cur = que.poll();

                for (int i = 0; i < size; i++) {
                    if (computers[cur][i] != 1) continue;
                    if (v[i]) continue;

                    v[i] = true;
                    que.offer(i);
                }
            }

        }
    }
}
