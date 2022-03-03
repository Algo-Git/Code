import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PRO_42578_위장 {
    public static void main(String[] args) {
        System.out.print(Solution.solution(new String[][]{{"yellowhat", "headgear"},
                {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}}));
    }

    static class Solution {
        public static int solution(String[][] clothes) {
            int answer = 1;
            Map<String, Integer> map = new HashMap<>();

            for (String[] cloth : clothes){ // 옷 종류마다 몇개의 옷이 있는지 map 에 put
                String key = cloth[1];
                if(!map.containsKey(key))
                    map.put(key, 2);    // 옷을 안입는 경우까지 추가
                else map.replace(key, map.get(key) + 1);
            }

            ArrayList<Integer> list = new ArrayList<>(map.values());    // map을 list로 변환

            for(int num : list){    // 옷 조합 개수 구하기
                answer *= num;
            }

            return answer - 1;  // 모두 없는 경우인 1을 제외
        }
    }
}