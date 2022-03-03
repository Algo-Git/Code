import java.util.*;

public class PRO_42577_전화번호목록 {
    public static void main(String[] args) {
        System.out.print(Solution.solution(new String[]{"119", "9767", "1195524"}));
    }

    static class Solution {
        public static boolean solution(String[] phone_book) {

            Arrays.sort(phone_book);    // 예를 들어, ["119", "1195524421", ...] 순서로 정렬

            Set<String> set = new HashSet<>();

            for(int i = 0; i < phone_book.length; i++){ // 전화번호 개수
                if(set.contains(phone_book[i])){    // "1195524421" 이 set에 포함되었으면 false 리턴
                    return false;
                }else{  // "1195524421" 이 set에 포함되지 않았으면 앞에부터 문자열 잘라서 set에 포함되었는지 확인
                    for(int j = 1; j <= phone_book[i].length(); j++){    // 현재 전화번호의 길이
                        String temp = phone_book[i].substring(0, j);
                        if(set.contains(temp)){
                            return false;
                        }
                    }
                    set.add(phone_book[i]); // set 에 넣기
                }
            }

            return true;
        }
    }
}