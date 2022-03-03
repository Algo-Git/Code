package PROGRAMMERS;

import java.util.*;

public class PRO_프린터_HoonyCode {

    class Solution {
        public int solution(int[] priorities, int location) {
            int answer = 1;

            Queue<Data> dataQueue = new LinkedList<>();
            for (int i = 0; i < priorities.length; i++) {
                dataQueue.add(new Data(i, priorities[i]));
            }

            Integer[] arr = Arrays.stream(priorities).boxed().toArray(Integer[]::new);
            Arrays.sort(arr, Collections.reverseOrder());

            Data cur;
            while (!dataQueue.isEmpty()){
                cur = dataQueue.poll();
                if (cur.priorities == arr[answer-1]){
                    if (cur.index == location)
                        break;
                    answer++;
                    continue;
                }

                dataQueue.offer(cur);
            }



            return answer;
        }
    }

    class Data {
        int index;
        int priorities;

        public Data(int index, int priorities) {
            this.index = index;
            this.priorities = priorities;
        }
    }
}
