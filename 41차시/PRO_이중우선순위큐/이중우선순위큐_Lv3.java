package com.ssafy.programas.heap;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 이중우선순위큐_Lv3 {
    public static void main(String[] args) {
        String[] operations = {"I 7","I 5","I -5","D -1"};
        int[] res = solution(operations);
        for (int i : res)
            System.out.print(i + " ");
    }

    public static int[] solution(String[] operations) {
        int[] answer = {0, 0};
        PriorityQueue<Integer> Max = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> Min = new PriorityQueue<>();

        for (int i = 0; i < operations.length; i++) {
            StringTokenizer st = new StringTokenizer(operations[i], " ");
            String str = st.nextToken();
            int number = Integer.parseInt(st.nextToken());
            //삭제
            if (str.equals("D")) {
                //최대값 삭제
                if (number == 1) {
                    if (!Max.isEmpty()) {
                        int delete = Max.peek();
                        Max.poll();
                        Min.remove(delete);
                    }
                } else {//최솟값 삭제
                    if (!Min.isEmpty()) {
                        int delete = Min.peek();
                        Min.poll();
                        Max.remove(delete);
                    }
                }
            } else { //추가
                Max.offer(number);
                Min.offer(number);
            }
        }
        if (!Max.isEmpty()) {
            answer[0] = Max.peek();
            answer[1] = Min.peek();
        }

        return answer;
    }
}
