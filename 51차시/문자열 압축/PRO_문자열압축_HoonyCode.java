package PRO;

public class PRO_문자열압축_HoonyCode {

    class Solution {
        public int solution(String s) {
            int answer = s.length();

            int len = s.length();
            for (int i = 1; i <= len / 2; i++) {

                //남은 문자열은 그냥 더해준다
                int tempAnswer = len % i;

                // 길이만큼 서브 문자열을 구한다
                String now = s.substring(0, i);

                int cnt = 1;
                for (int j = 1; j < len / i; j++) {
                    String next = s.substring(j * i, (j + 1) * i);

                    // 문자열이 마지막이라면
                    if ((j + 1) == len / i) {
                        if (now.equals(next)) { // 같을 때
                            tempAnswer = tempAnswer + i + String.valueOf(cnt + 1).length();
                        } else {//같지 않을 때
                            if (cnt > 1) tempAnswer = tempAnswer + String.valueOf(cnt).length();
                            tempAnswer = tempAnswer + i * 2;
                        }

                        break;
                    }

                    // 현재 문자열과 다음 문자열리 같다면
                    if (now.equals(next)) {
                        cnt++;
                    } else { // 문자열이 달라진다면
                        if (cnt > 1) tempAnswer = tempAnswer + i + String.valueOf(cnt).length();
                        else tempAnswer = tempAnswer + i;
                        now = next;
                        cnt = 1; // 같다는 표시를 초기화 시킨다.
                    }
                }

                answer = Math.min(answer, tempAnswer);
            }

            return answer;
        }
    }
}
