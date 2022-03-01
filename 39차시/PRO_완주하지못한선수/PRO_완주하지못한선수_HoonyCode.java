package PROGRAMMERS;
import java.util.*;

public class PRO_완주하지못한선수_HoonyCode {

    class Solution {
        public String solution(String[] participant, String[] completion) {
            Arrays.sort(participant);
            Arrays.sort(completion);

            int i;
            for (i = 0 ; i < completion.length ; i++){
                if (!participant[i].equals(completion[i])){
                    return participant[i];
                }
            }
            return participant[i];
        }
    }
}
