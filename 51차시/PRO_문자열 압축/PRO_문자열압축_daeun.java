public class PRO_60057_문자열압축 {
    public static void main(String[] args) {
        System.out.print(solution("aabbaccc"));
    }

    public static int solution(String s) {
        int n = s.length();
        int answer = n;

        String here;
        for (int i = 1; i <= n; i++) {
            String temp = s.substring(0, i);
            StringBuilder sb = new StringBuilder();
            int cnt = 1;
            for (int j = i; j <= n; j = j + i) {
                if(j + i <= n) here = s.substring(j, j + i); // j + i 가 전체 문자열 길이 안일때
                else here = s.substring(j, n);  // 문자열 길이가 벗어나게 자르지 않기 위함
                if (!temp.equals(here)) {   // 같지 않은 문자열을 만났을 때
                    if (cnt == 1) {  // 1개만 같다면
                        sb.append(temp);    // 숫자 제외한 문자만 StringBuilder 에 추가
                    } else {    // 여러번 같다면
                        sb.append(cnt).append(temp);    // 숫자 + 문자열 추가
                        cnt = 1;
                    }
                    temp = here;    // temp 변경
                } else cnt++;  // 같은 문자열이라면
            }

            // 마지막 문자열 처리
            if (cnt == 1) {  // 1개만 같다면
                sb.append(temp);    // 숫자 제외한 문자만 StringBuilder 에 추가
            } else {    // 여러번 같다면
                sb.append(cnt).append(temp);    // 숫자 + 문자열 추가
            }

            answer = Math.min(answer, sb.length());
        }

        return answer;
    }
}