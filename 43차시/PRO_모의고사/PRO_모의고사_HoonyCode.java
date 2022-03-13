package PROGRAMMERS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PRO_모의고사_HoonyCode {

    class Solution {
        public int[] solution(int[] answers) {
            int[][] pick = {
                    {1, 2, 3, 4, 5},
                    {2, 1, 2, 3, 2, 4, 2, 5},
                    {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
            };
            int[] score = new int[3];
            for (int i = 0; i < answers.length; i++) {
                if (answers[i] == pick[0][i % 5]) score[0]++;
                if (answers[i] == pick[1][i % 8]) score[1]++;
                if (answers[i] == pick[2][i % 10]) score[2]++;
            }

            int Max = Arrays.stream(score).max().orElse(0);

            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                if (Max == score[i]) {
                    list.add(i + 1);
                }
            }



            return list.stream().mapToInt(Integer::intValue).toArray();
        }
    }
}
