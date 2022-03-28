package PROGRAMMERS;


import java.util.LinkedList;
import java.util.Queue;

public class PRO_단어변환_HoonyCode {
    class Solution {
        public int solution(String begin, String target, String[] words) {
            int answer = 100;
            Queue<Pair> queue = new LinkedList<>();
            queue.offer(new Pair(0, begin));
            int len = words.length;
            Pair cur;

            while (!queue.isEmpty()) {
                cur = queue.poll();

                if (cur.str.equals(target)) {
                    answer = cur.cnt;
                    break;
                }
                if (cur.cnt > len){
                    continue;
                }

                for (String word : words) {
                    int cnt = 0;

                    for (int i = 0 ; i < begin.length() ; i++){
                        if (word.charAt(i) != cur.str.charAt(i)) {
                            cnt++;
                        }
                    }

                    if (cnt == 1){
                        queue.offer(new Pair(cur.cnt + 1, word));
                    }
                }

            }

            return answer == 100 ? 0 : answer;
        }
    }

    class Pair{
        int cnt;
        String str;

        public Pair(int cnt, String str) {
            this.cnt = cnt;
            this.str = str;
        }
    }

}
