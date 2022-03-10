package PROGRAMMERS;


public class PRO_HIndex {

    class Solution {
        public int solution(int[] citations) {
            int answer = 0;
            int total = citations.length;
            int count = 0;

            int[] cnt = new int[10_001];

            for (int idx : citations) {
                cnt[idx]++;
            }

            for (int i = 0; i < 10_001; i++) {
                int now = cnt[i];
                total -= count;
                count += now;

                if (total >= i && count <= i)
                    Math.max(answer, i);
            }

            return answer;
        }
    }
}
