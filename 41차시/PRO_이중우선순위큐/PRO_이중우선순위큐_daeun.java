import java.util.*;

public class PRO_42628_이중우선순위큐 {
    public static void main(String[] args) {

        System.out.print(Solution.solution(new String[]{"I 16", "D 1"}));
    }

    static class Solution {
        public static int[] solution(String[] operations) {

            PriorityQueue<Integer> pqMin = new PriorityQueue<>();
            PriorityQueue<Integer> pqMax = new PriorityQueue<>(
                    Collections.reverseOrder()
            );  // 내림차순

            for (String s : operations) {
                // 삽입
                if (s.charAt(0) == 'I') {
                    pqMin.offer(Integer.parseInt(s.substring(2, s.length())));
                    pqMax.offer(Integer.parseInt(s.substring(2, s.length())));
                    continue;
                }
                // 최솟값 삭제
                if (s.charAt(2) == '-') {
                    if (pqMin.isEmpty()) continue;    // 최소 우선순위큐가 비면 continue
                    int min = pqMin.poll();
                    pqMax.remove(min);   // 최대 우선순위큐에서 삭제
                }
                // 최댓값 삭제
                else {
                    if (pqMax.isEmpty()) continue;    // 최대 우선순위큐가 비면 continue
                    int max = pqMax.poll();
                    pqMin.remove(max);   // 최소 우선순위큐에서 삭제
                }
            }

            int[] answer = new int[2];
            if (!pqMax.isEmpty()) {
                answer[0] = pqMax.peek();   // 최댓값
                answer[1] = pqMin.peek();   // 최솟값
            }

            return answer;
        }
    }
}