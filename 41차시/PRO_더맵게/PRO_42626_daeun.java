import java.util.*;

public class PRO_42626_더맵게 {
    public static void main(String[] args) {

        System.out.print(Solution.solution(new int[]{1, 2, 3, 9, 10, 12}, 7));

    }

    static class Solution {
        public static int solution(int[] scoville, int K) {
            int answer = 0;

            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int temp : scoville) {
                pq.offer(temp);
            }

            while (pq.size() > 1) {
                Integer cur1 = pq.poll();
                Integer cur2 = pq.poll();

                if (cur1 >= K) break;

                pq.offer(cur1 + cur2 * 2);
                answer++;
            }

            if (!pq.isEmpty() && pq.peek() < K) answer = -1;

            return answer;
        }
    }
}