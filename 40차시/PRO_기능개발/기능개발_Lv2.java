package com.ssafy.programas.stackAndQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 기능개발_Lv2 {
    public static void main(String[] args) {
        int[] progresses = {95, 90, 99, 99, 80, 99};
        int[] speeds = {1, 1, 1, 1, 1, 1};
        int[] res = solution(progresses, speeds);

        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }

    static public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> list = new ArrayList<>();
        int size = progresses.length;
        for (int i = 0; i < size; i++) {
            int day = (100 - progresses[i]) / speeds[i];
            if(((100 - progresses[i]) / speeds[i])!=0)
                day++;
            list.add(day);
        }

        Queue<Integer> store = new LinkedList<>();
        int d = list.get(0);
        int dayCnt = 1;
        //5, 10, 1, 1, 20, 1
        for (int i = 1; i < list.size(); i++) {
            if (d < list.get(i)) {
                d = list.get(i);
                store.add(dayCnt);
                dayCnt = 1;
            } else {
                dayCnt++;
            }

        }

        store.add(dayCnt);
        int[] answer = new int[store.size()];
        int i = 0;
        while (!store.isEmpty()) {
            answer[i] = store.poll();
            i++;
        }

        return answer;
    }
}
