import java.util.*;

public class PRO_42839_소수찾기 {
    public static void main(String[] args) {

//        System.out.print(Solution.solution("17"));
//        System.out.print(Solution.solution("011"));
        System.out.print(Solution.solution("0002"));
    }

    static class Solution {
        static Set<Integer> set = new HashSet<>();
        static String s;
        static boolean[] v;

        public static int solution(String numbers) {
            int answer = 0;

            s = numbers;
            v = new boolean[s.length()];
            subset(0, "0");

            for (int temp : set) {
                // 소수 확인
                int div = temp / 2;
                boolean check = false;
                while (true) {
                    if (div <= 1) break;
                    if (temp % div == 0) {  // 나눠지는 수가 있으면 소수가 아님을 체크
                        check = true;
                        break;
                    }
                    div--;
                }
                if (!check && temp > 1) answer++;   // 1 이상일 때, 소수이면 answer + 1
            }

            return answer;
        }

        static void subset(int cnt, String num) {
            if (cnt == s.length()) {    // 문자열 길이가 되면 set 에 넣고 리턴
                set.add(Integer.parseInt(num));
                return;
            }

            subset(cnt + 1, num);

            for (int i = 0; i < s.length(); i++) {
                if (v[i]) continue;
                int temp = Integer.parseInt(num + s.charAt(i));

                // 부분집합
                v[i] = true;
                subset(cnt + 1, Integer.toString(temp));
                v[i] = false;
            }
        }
    }
}