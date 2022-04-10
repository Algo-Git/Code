package PRO;


import java.util.Arrays;

public class PRO_43236_징검다리 {

    class Solution {
        public int solution(int distance, int[] rocks, int n) {
            int answer = 0;

            Arrays.sort(rocks);

            int start = 0;
            int end = distance;

            while (start <= end) {
                int mid = (start + end) / 2;
                int remove = 0;
                int prev = 0;

                for (int i = 0; i < rocks.length; i++) {

                    if (rocks[i] - prev < mid) {
                        remove++;
                    } else {
                        prev = rocks[i];
                    }

                }

                if (distance - prev < mid) remove++;

                if (remove <= n) {
                    answer = Math.max(answer, mid);

                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }


            return answer;
        }
    }
}
