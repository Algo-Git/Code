package PROGRAMMERS;


public class PRO_조이스틱_HoonyCode {

    public static void main(String[] args) {
        System.out.println("Solution.solution() = " + Solution.solution("JEROEN"));
    }

    static class Solution {

        static int moveCnt = Integer.MAX_VALUE;

        static public int solution(String name) {
            int answer = 0;
            char[] chars = name.toCharArray();
            boolean[] v = new boolean[chars.length];
            int NotACnt = 0;

            // 1. 알파벳을 어떻게 가장 적게 변환시키는지 찾는것
            for (int i = 0; i < chars.length; i++) {
                char in = chars[i];
                answer += Math.min(in - 'A', 'Z' - in + 1);
                if (i != 0 && in != 'A') {
                    v[i] = true;
                    NotACnt++;
                }
            }


            // 2. 위치를 찾는 부분
            //가는 방법은 왼쪽 오른쪽 탐색이 있다;
            dfs(0, NotACnt, 0, chars.length, 0, v);
            answer += moveCnt;


            return answer;
        }

        private static void dfs(int cnt, int end, int now_position, int len, int count, boolean[] v) {
            // 중간 가지치기 조건
            if (count >= moveCnt) return;

            if (cnt == end) {
                moveCnt = count;
                return;
            }

            // 오른쪽으로 갈떄
            for (int i = now_position + 1; i < len + len; i++) {
                if (v[i % len]) {
                    v[i % len] = false;
                    dfs(cnt + 1, end, i % len, len, count + i - now_position, v);
                    v[i % len] = true;
                }
            }

            //왼쪽으로 갈 떄
            int temp_cnt = 0;
            for (int i = now_position - 1; i > -len; i--) {
                temp_cnt++;
                int temp_i = i;
                if (i < 0)
                    temp_i = len + i;

                if (v[temp_i]) {
                    v[temp_i] = false;
                    dfs(cnt + 1, end, temp_i, len, count + temp_cnt, v);
                    v[temp_i] = true;
                }
            }
        }
    }
}
