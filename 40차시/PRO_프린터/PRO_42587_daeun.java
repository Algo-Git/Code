import java.util.*;

public class PRO_42587_프린터 {
    public static void main(String[] args) {
        System.out.print(Solution.solution(new int[]{2, 1, 3, 2}, 2));
    }

    static class Solution {
        public static int solution(int[] priorities, int location) {
            int answer = 1;

            PriorityQueue<Integer> pq = new PriorityQueue<>(
                    (o1, o2) -> (Integer.compare(o2, o1)));
            for (int i = 0; i < priorities.length; i++) {
                pq.offer(priorities[i]);
            }

            end:
            while (!pq.isEmpty()) {
                // 배열의 순서대로 큐에 삽입되므로 순서대로 확인
                for (int i = 0; i < priorities.length; i++) {
                    if (pq.peek() == priorities[i]) {
                        if (i == location) break end;
                        pq.poll(); // 같은 중요도를 가진 것 모두 큐에서 제거
                        answer++;
                    }
                }
            }

            return answer;
        }
    }
}