package PROGRAMMERS;

import java.util.*;

public class PRO_가장큰수_HoonyCode {

    class Solution {
        public String solution(int[] numbers) {
            StringBuilder answer = new StringBuilder();

            String[] in = new String[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                in[i] = String.valueOf(numbers[i]);
            }

            Arrays.sort(in, (o1, o2) ->
                    (o2 + o1).compareTo(o1 + o2)
            );

            if (in[0].charAt(0) == '0')
                return "0";

            for (int i = 0 ; i < in.length; i++)
                answer.append(in[i]);

            return answer.toString();
        }
    }

}
