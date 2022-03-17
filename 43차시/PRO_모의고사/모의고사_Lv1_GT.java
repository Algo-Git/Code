package com.ssafy.programas.brute_force;


import java.util.ArrayList;
import java.util.List;

/*
1번 수포자 1 2 3 4 5
2번 수포자 2 1 2 3 2 4 2 5
3번 수포자 3 3 1 1 2 2 4 4 5 5
 */
public class 모의고사_Lv1_GT {
    public static void main(String[] args) {
        int[] answers = {1, 3, 2, 4, 2};
        int[] res = solution(answers);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }

    }

    public static int[] solution(int[] answers) {
        int[] math1 = {1, 2, 3, 4, 5};
        int[] math2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] math3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int cnt1 = checkCnt(math1, answers);
        int cnt2 = checkCnt(math2, answers);
        int cnt3 = checkCnt(math3, answers);

        int max = Math.max(cnt1, Math.max(cnt2, cnt3));
        List<Integer> list = new ArrayList<>();
        if (max == cnt1) list.add(1);
        if (max == cnt2) list.add(2);
        if (max == cnt3) list.add(3);


        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    public static int checkCnt(int[] math, int[] answers) {
        int res = 0;
        int k = 0;
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == math[k]) {
                res++;
            }
            k++;
            if (k == math.length)
                k = 0;
        }
        return res;
    }
}
