package com.ssafy.programas.heap;

import java.util.PriorityQueue;

/*
썩은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
 */
public class 더맵게_Lv2 {
    public static void main(String[] args) {
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int k = 7;
        System.out.println(solution(scoville, k));
    }

    public static int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : scoville) {
            pq.offer(i);
        }

        while (pq.peek() < K) {
            //모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우에는 -1을 return 합니다.
            if (pq.size() < 2) return -1;//ㅇㅂㅇ?? 이걸 못봤네

            int p = pq.poll();
            int q = pq.poll();
            int res = p + (q * 2);
            pq.offer(res);
            answer++;
        }

        return answer;
    }
}
