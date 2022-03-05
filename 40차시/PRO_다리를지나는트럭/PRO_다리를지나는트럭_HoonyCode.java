package PROGRAMMERS;

import java.util.*;


public class PRO_다리를지나는트럭_HoonyCode {


    class Solution {
        public int solution(int bridge_length, int weight, int[] truck_weights) {
            int answer = 0;

            List<Data> list = new LinkedList<>();

            int totalWeight = 0;
            int idx = 0;
            while (idx != truck_weights.length) {


                // 클때;
                if (totalWeight + truck_weights[idx] > weight) {
                    Data cur = list.remove(0);
                    totalWeight -= cur.weight;
                    int time = bridge_length + 1 - cur.location;
                    answer += time;
                    for (Data data : list) {
                        data.location += time;
                    }
                    if (totalWeight + truck_weights[idx] <= weight){
                        list.add(new Data(truck_weights[idx], 1));
                        totalWeight += truck_weights[idx];
                        idx++;
                    }
                    continue;
                }

                // 크지 않을때
                answer++;
                if (!list.isEmpty() && list.get(0).location + 1 > bridge_length) {
                    Data remove = list.remove(0);
                    totalWeight -= remove.weight;
                }
                for (Data cur : list)
                    cur.location += 1;
                list.add(new Data(truck_weights[idx], 1));
                totalWeight += truck_weights[idx];
                idx++;

            }

            answer += bridge_length - list.get(list.size()-1).location + 1;



            return answer;
        }
    }

    class Data {
        int weight;
        int location;

        public Data(int weight, int location) {
            this.weight = weight;
            this.location = location;
        }
    }

}
