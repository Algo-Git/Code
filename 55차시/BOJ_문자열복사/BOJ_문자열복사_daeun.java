import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2195_문자열복사 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String P = br.readLine();

        String here;
        int start = 0;
        int answer = 1; //  정답 (1번 복사는 무조건 있으므로 1로 초기화)
        for (int i = 1; i <= P.length(); i++) {
            here = P.substring(start, i);
            // copy 횟수를 줄이기 위해 substring이 S에 포함된 문자열이면 continue
            if (S.contains(here)) continue;
            answer++;   // S에 포함된 문자열이 아니므로, 이전까지의 문자열을 copy 횟수 + 1
            start = i - 1;  // 시작 인덱스를 i - 1로
            i--;    // 끝 인덱스를 i - 1로 변경해 다음 끝 인덱스를 다시 i까지로
        }

        System.out.print(answer);
    }
}