public class PRO_43163_단어변환 {
    public static void main(String[] args) {

        System.out.print(Solution.solution(
                "hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"}
        ));

    }

    static class Solution {

        static int len, res;
        static boolean[] v;

        public static int solution(String begin, String target, String[] words) {
            int answer = 0;

            len = begin.length();
            v = new boolean[words.length];
            res = Integer.MAX_VALUE;
            dfs(begin, target, words, 0);

            if(res == Integer.MAX_VALUE) answer = 0;
            else answer = res;

            return answer;
        }

        private static void dfs(String begin, String target, String[] words, int total) {
            if(begin.equals(target)){
                res = Math.min(res, total);
                return;
            }

            for (int i = 0; i < words.length; i++) {
                if (v[i]) continue;

                int cnt = 0;    // 다른 글자 개수
                for (int j = 0; j < len; j++){
                    if(begin.charAt(j) != words[i].charAt(j)) cnt++;
                }

                if(cnt == 1){
                    v[i] = true;
                    dfs(words[i], target, words, total + 1);
                    v[i] = false;
                }
            }
        }

    }
}