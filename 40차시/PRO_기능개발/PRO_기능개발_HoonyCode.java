package PROGRAMMERS;

import java.util.ArrayList;
import java.util.List;

public class PRO_기능개발_HoonyCode {

    class Solution {
        public int[] solution(int[] progresses, int[] speeds) {
            List<Integer> answer = new ArrayList<>();

            int start = 0;
            int end = progresses.length;
            
            while (start != end) {
                for (int i = start; i < end; i++) {
                    progresses[i] += speeds[i];
                }

                int cnt = 0;

                for (int i = start; i < end; i++) {
                    if (progresses[i] > 99){
                        start++;
                        cnt++;
                    }else
                        break;
                }

                if (cnt > 0)
                    answer.add(cnt);
            }


            return answer.stream().mapToInt(Integer::intValue).toArray();
        }
    }
}
