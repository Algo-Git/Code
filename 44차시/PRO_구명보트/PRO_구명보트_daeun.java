import java.util.Arrays;

public class PRO_42885_구명보트 {
    public static void main(String[] args) {
//        System.out.print(Solution.solution(new int[]{70,50,80,50}, 100));
        System.out.print(Solution.solution(new int[]{70,80,50}, 100));
    }

    static class Solution {
        public static int solution(int[] people, int limit) {
            int answer = 0;

            Arrays.sort(people);

            int maxIdx = people.length - 1;
            for (int i = 0; i < people.length - 1; i++){
                if(i != maxIdx && people[maxIdx] + people[i] <= limit){
                    answer++;
                    maxIdx--;
                }else {
                    answer++;
                    maxIdx--;
                    i--;
                }

                if(i == maxIdx) break;
            }

            return answer;
        }
    }
}
