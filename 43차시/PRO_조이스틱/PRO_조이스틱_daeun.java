public class PRO_42860_조이스틱 {
    public static void main(String[] args) {

//        System.out.print(Solution.solution("JAZ"));
//        System.out.print(Solution.solution("JEROEN"));
//        System.out.print(Solution.solution("JAN"));
        System.out.print(Solution.solution("BBAAABAAAAAAAAAAAABA"));    // 답 13
    }

    static class Solution {

        static int cnt, res;
        static String s;
        static boolean[] v;

        public static int solution(String name) {
            int answer = 0;

            for (int i = 0; i < name.length(); i++) {
                if (name.charAt(i) != 'A')
                    cnt++;  // 바꿔야할 알파벳 개수
            }

            res = Integer.MAX_VALUE;
            s = name;
            v = new boolean[name.length()];
            v[0] = true;    // 현재 알파벳 방문 체크
            dfs(0, 0, 0);
            answer = res;

            return answer;
        }

        static void dfs(int idx, int depth, int total) {
            // 현재 바꿀 알파벳이 A가 아닐 때
            int hereCnt = 0;
            if(s.charAt(idx) != 'A'){
                int dist = s.charAt(idx) - 'A';
                hereCnt = Math.min(dist, 26 - dist);    // A에서 현재 바꿀 알파벳까지 최소거리 구하기
                depth++;    // 바꾼 알파벳 개수 + 1
            }

            if (depth == cnt) { // 바꿀 알파벳을 모두 바꿨을 때
                res = Math.min(res, total + hereCnt);   // 조이스틱 조작 횟수 최소값
                return;
            }

            // 왼쪽
            int left = 1;   // 몇칸 가는지
            int leftIdx = idx - 1;  // 이동할 인덱스
            boolean check1 = false;
            for (int i = 1; i < s.length(); i++) {
                if (leftIdx <= -1) leftIdx = s.length() - 1;    // 범위 벗어나면 끝 인덱스로
                if (!v[leftIdx] && s.charAt(leftIdx) != 'A') {  // A가 아닌 바꿀 알파벳을 구하면
                    check1 = true;
                    break;
                }
                left++; // 이동할 칸 개수 + 1
                leftIdx--;  // 이동할 칸 인덱스 - 1
            }

            // 오른쪽
            int right = 1;  // 몇칸 가는지
            int rightIdx = idx + 1; // 이동할 인덱스
            boolean check2 = false;
            for (int i = 1; i < s.length(); i++) {
                if (rightIdx >= s.length()) rightIdx = 0;   // 범위 벗어나면 0번 인덱스로
                if (!v[rightIdx] && s.charAt(rightIdx) != 'A') {    // A가 아닌 바꿀 알파벳을 구하면
                    check2 = true;
                    break;
                }
                right++;    // 이동할 칸 개수 + 1
                rightIdx++; // 이동할 칸 인덱스 + 1
            }

            if(check1){ // 왼쪽으로 이동해서 바꿀 알파벳이 있으면
                v[leftIdx] = true;
                // hereCnt : 현재 알파벳에서 바꿀 알파벳까지의 거리
                // left : 왼쪽으로 이동할 거리
                dfs(leftIdx, depth, total + hereCnt + left);
                v[leftIdx] = false;
            }

            if(check2){ // 오른쪽으로 이동해서 바꿀 알파벳이 있으면
                v[rightIdx] = true;
                // hereCnt : 현재 알파벳에서 바꿀 알파벳까지의 거리
                // right : 오른쪽으로 이동할 거리
                dfs(rightIdx, depth, total + hereCnt + right);
                v[rightIdx] = false;
            }
        }
    }
}