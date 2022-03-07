package PROGRAMMERS;

import java.util.PriorityQueue;

public class PRO_더맵게_HoonyCode {

    //    섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
    class Solution {
        public int solution(int[] scoville, int K) {
            int answer = 0;
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
            for (int in : scoville) {
                priorityQueue.add(in);
            }

            while (priorityQueue.peek() < K) {
                if (priorityQueue.size() == 1 && priorityQueue.peek() < K) {
                    answer = -1;
                    break;
                }

                priorityQueue.add(priorityQueue.poll() + 2 * priorityQueue.poll());
                answer++;
            }

            return answer;
        }
    }
}
