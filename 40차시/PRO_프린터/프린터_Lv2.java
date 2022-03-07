package com.ssafy.programas.stackAndQueue;

import java.util.Collections;
import java.util.PriorityQueue;

/*
https://coding-factory.tistory.com/603
 */
public class 프린터_Lv2 {
    public static void main(String[] args) {
        int[] priorities = {2, 1, 3, 2};
        int location = 2;

        System.out.println(solution(priorities, location));
    }

    static public int solution(int[] priorities, int location) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); //우선순위가 높은 문자수
        int answer = 0;

        for (int priority : priorities) {
            pq.add(priority);
        }
        while (!pq.isEmpty()) {
            for (int i = 0; i < priorities.length; i++) {
                if (priorities[i] == pq.peek()) {
                    if (i == location) {
                        answer++;
                        return answer;
                    }
                    pq.poll();
                    answer++;
                }
            }
        }

        return 1;
    }
}
