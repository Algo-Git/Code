package PROGRAMMERS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PRO_K번쨰수_HoonyCode {

    class Solution{
        public int[] solution(int[] array, int[][] commands){
            int[] answer = new int[commands.length];


            List<Integer> list = new ArrayList<>();
            for (int i = 0 ; i < commands.length; i++){
                list.clear();

                for (int j = commands[i][0]-1 ; j < commands[i][1] ; j++){
                    list.add(array[j]);
                }

                Collections.sort(list);

                answer[i] = list.get(commands[i][2] - 1);
            }

            return answer;
        }
    }
}
