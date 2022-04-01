package PRO;

import java.lang.reflect.Array;
import java.util.Arrays;

public class PRO_입국심사_HoonyCode {
    class Solution {
        public long solution(int n, int[] times) {
            long answer = 0;

            // n 입국 심사를 기다리는 사람
            // 추정 시간값 : 각 심사관 별 심사시간 = 심사관 당 맡을 수 있는 입국자 수
            Arrays.sort(times);

            long start = 1;
            long end = n * (long)times[times.length-1];
            long sum;

            while(start <= end){
                sum = 0;
                long mid = (start + end) / 2;

                for(int time : times){
                    sum += mid / time;
                }

                if(sum < n){
                    start = mid + 1;
                }else{
                    end = mid - 1;
                }
            }

            answer = start;

            return answer;
        }
    }
}
