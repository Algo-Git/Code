package PROGRAMMERS;
import java.util.*;
public class PRO_위장_HoonyCode {

    class Solution {
        public int solution(String[][] clothes) {
            int answer = 1;

            Map<String, Integer> map = new HashMap<>();
            //clothes의 각 행은 [의상의 이름, 의상의 종류]
            //같은 이름을 가진 의상은 존재하지 않습니다.
            for (String[] clothe : clothes) {
                if (map.get(clothe[1]) == null) {
                    map.put(clothe[1], 2);
                } else
                    map.replace(clothe[1], map.get(clothe[1]) + 1);
            }

            for (Integer num : map.values()) {
                answer *= num;
            }

            return answer - 1;

        }
    }
}
