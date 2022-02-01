import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_15927_회문은회문아니야 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int res = -1;

        String first = s.substring(0, s.length() >> 1);
        StringBuilder sec = new StringBuilder();
        sec.append(s.substring((s.length() & 1) == 1 ? (s.length() >> 1) + 1 : s.length() >> 1, s.length()));
        sec.reverse();

        // 팰린드롬일 때
        if (first.equals(sec.toString())) {
            boolean check = true;
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) != s.charAt(0)) {
                    check = false;
                    break;
                }
            }
            if (!check) {  // 모든 문자 같지 않을 때
                res = s.length() - 1;
            }
        } else {
            res = s.length();
        }

        System.out.print(res);
    }
}