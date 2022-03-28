import java.util.*;

public class PRO_43164_여행경로 {
    public static void main(String[] args) {

        System.out.print(Solution.solution(
                new String[][]{{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}}
        ));

    }

    static class Solution {

        static int n;
        static boolean[] v;
        static ArrayList<String> list;

        public static String[] solution(String[][] tickets) {
            n = tickets.length;
            String[] answer = new String[n + 1];

            v = new boolean[n];
            list = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                if (tickets[i][0].equals("ICN")) {
                    v[i] = true;
                    dfs(i, 0, tickets, "ICN");
                    v[i] = false;
                }
            }

            Collections.sort(list);

            String res = list.get(0);
            for (int i = 0; i < n + 1; i++) {
                answer[i] = res.substring(i * 3, i * 3 + 3);
            }

            return answer;
        }

        private static void dfs(int idx, int cnt, String[][] tickets, String total) {
            if (cnt == n - 1) {
                list.add(total + tickets[idx][1]);
                return;
            }

            for (int i = 0; i < n; i++) {
                if (v[i]) continue;
                if (!tickets[i][0].equals(tickets[idx][1])) continue;
                v[i] = true;
                dfs(i, cnt + 1, tickets, total + tickets[i][0]);
                v[i] = false;
            }
        }
    }
}