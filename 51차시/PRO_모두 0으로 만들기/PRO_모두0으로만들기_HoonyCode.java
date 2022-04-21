package PRO;


import java.util.ArrayList;
import java.util.Arrays;

public class PRO_모두0으로만들기_HoonyCode {

    class Solution {

        long answer;
        boolean[] v;
        long[] long_a;
        ArrayList<Integer>[] list;

        public long solution(int[] a, int[][] edges) {
            int len = a.length;
            this.answer = 0;
            this.v = new boolean[len];
            this.list = new ArrayList[len];
            this.long_a = new long[len];

            long sum = 0;
            for (int i = 0; i < len; i++) {
                sum += a[i];
                list[i] = new ArrayList<>();
                long_a[i] = a[i];
            }

            if (sum != 0) return -1;

            for (int i = 0; i < edges.length; i++) {
                list[edges[i][0]].add(edges[i][1]);
                list[edges[i][1]].add(edges[i][0]);
            }

            dfs(0);


            return answer;
        }

        private long dfs(int now) {
            this.v[now] = true;


            for (int i = 0; i < list[now].size(); i++) {
                int next = list[now].get(i);
                if (!v[next]) long_a[now] += dfs(next);
            }

            this.answer += Math.abs(long_a[now]);

            return long_a[now];
        }

    }

}
