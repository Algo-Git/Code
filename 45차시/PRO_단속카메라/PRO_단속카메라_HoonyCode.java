package PROGRAMMERS;

import java.util.Arrays;
import java.util.Comparator;

public class PRO_단속카메라_HoonyCode {

    class Solution {
        public int solution(int[][] routes) {
            int answer = 0;

            Arrays.sort(routes, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return Integer.compare(o1[1], o2[1]);
                }
            });

            int cam = Integer.MIN_VALUE;

            for (int[] route : routes) {
                if(cam < route[0]) {
                    cam = route[1];
                    answer++;
                }
            }

            return answer;
        }
    }

}
