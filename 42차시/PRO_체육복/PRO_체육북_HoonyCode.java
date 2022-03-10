package PROGRAMMERS;

import java.util.Arrays;

public class PRO_체육북_HoonyCode {
    class Solution {
        public int solution(int n, int[] lost, int[] reserve) {
            int answer = 0;

            int[] list = new int[n + 2];
            Arrays.fill(list, 1);


            for (int idx : lost) {
                list[idx]--;
            }

            for (int idx : reserve) {
                list[idx]++;
            }

            for (int i = 1; i <= n; i++) {
                if (list[i] == 2) {
                    if (list[i - 1] == 0) {
                        list[i]--;
                        list[i - 1]++;
                        continue;
                    }

                    if (list[i + 1] == 0) {
                        list[i]--;
                        list[i + 1]++;
                        continue;
                    }
                }
            }

            for (int i = 1; i <= n; i++) {
                if (list[i] > 0)
                    answer++;
            }

            return answer;
        }
    }
}
