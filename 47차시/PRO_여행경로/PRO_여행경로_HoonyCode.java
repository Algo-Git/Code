package PROGRAMMERS;

import java.util.*;

public class PRO_여행경로_HoonyCode {

    class Solution {

        boolean[] v;
        String[] answer;
        boolean flag;

        public String[] solution(String[][] tickets) {
            Arrays.sort(tickets, new Comparator<String[]>() {
                @Override
                public int compare(String[] o1, String[] o2) {
                    if (o1[0].equals(o2[0])) {
                        return o1[1].compareTo(o2[1]);
                    }
                    return o1[0].compareTo(o2[0]);
                }
            });

            int len = tickets.length;
            answer = new String[len + 1];
            v = new boolean[len + 1];

            answer[0] = "ICN";
            dfs(0, len, "ICN", tickets);

            return answer;
        }

        public void dfs(int depth, int len, String start, String[][] tickets) {

            if (depth == len) {
                flag = true;
                return;
            }

            for (int i = 0; i < len; i++) {
                if (v[i]) continue;
                if (start.equals(tickets[i][0])) {
                    v[i] = true;
                    answer[depth + 1] = tickets[i][1];
                    dfs(depth + 1, len, tickets[i][1], tickets);
                    if (flag) return;
                    v[i] = false;
                }
            }
        }
    }
}
