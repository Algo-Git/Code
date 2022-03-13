package PROGRAMMERS;

public class PRO_카펫_HoonyCode {

    class Solution {
        public int[] solution(int brown, int yellow) {
            int[] answer = new int[2];

            int total = brown + yellow;

            int w, h;
            for (int i = 3; i <= total; i++) {
                if (total % i != 0) continue;
                w = i;
                h = total / i;

                int b = w << 2 + h << 2 - 4;
                int y = (w - 2) * (h - 2);

                if (b == brown && y == yellow) {
                    answer[0] = Math.max(w, h);
                    answer[1] = Math.min(w, h);
                    break;
                }
            }

            return answer;
        }
    }
}
