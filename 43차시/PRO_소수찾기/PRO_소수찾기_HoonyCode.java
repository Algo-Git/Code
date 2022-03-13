package PROGRAMMERS;

import java.util.HashSet;

public class PRO_소수찾기_HoonyCode {

    class Solution {
        public int solution(String numbers) {
            int answer = 0;
            final int max = 10000000;

            boolean[] sosu = new boolean[max];
            sosu[0] = true;
            sosu[1] = true;
            //소수 만들기
            for (int i = 2 ; i < max ; i++){
                if (sosu[i]) continue;
                for (int j = 2 ; j * i < max ; j++ ){
                    sosu[i*j] = true;
                }
            }

            HashSet<Integer> set = new HashSet<>();
            char[] chars = numbers.toCharArray();
            int len = chars.length;
            boolean[] v = new boolean[len];

            dfs(0, len, v, chars, set, "0");

            for (Integer num : set) {
                if (!sosu[num]) answer++;
            }

            return answer;
        }

        private void dfs(int dept, int len, boolean[] v, char[] chars, HashSet<Integer> set, String s) {


            if (dept == len) {
                set.add(Integer.valueOf(s));
                return;
            }

            //넘어갈때
            dfs(dept + 1, len, v, chars, set, s);


            for (int i = 0 ; i < len; i++){
                if (v[i]) continue;
                v[i] = true;
                dfs(dept + 1, len, v, chars, set, s + chars[i]);
                v[i] = false;
            }

        }
    }
}
