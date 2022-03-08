package PROGRAMMERS;

import java.util.*;

public class PRO_이중우선순위큐_HoonyCode {

    class Solution {
        public int[] solution(String[] operations) {
            int[] answer = new int[2];
            PriorityQueue<Integer> min = new PriorityQueue<>();
            PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());

            for (String op : operations) {
                String[] in = op.split(" ");

                if (in[0].equals("I")) {
                    int value = Integer.parseInt(in[1]);
                    max.offer(value);
                    min.offer(value);
                } else { // D 일때
                    if (min.isEmpty()) continue; // 무시

                    int value = Integer.parseInt(in[1]);
                    if (value < 0) { // 최솟값 삭제
                        max.remove(min.poll());
                    } else { // 최댓값 삭제
                        min.remove(max.poll());
                    }
                }
            }

            if (!min.isEmpty()) {
                answer = new int[]{max.poll(), min.poll()};
            }

            return answer;
        }
    }
}
