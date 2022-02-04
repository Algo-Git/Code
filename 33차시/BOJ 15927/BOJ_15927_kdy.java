import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N15927 {// BOJ 15927. 회문은 회문아니야!!

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();// 문자열
        String tmp = str.substring(0);// 문자열 복사
        if (tmp.replaceAll(tmp.substring(0, 1), "").length() == 0) {// 문자열이 모두 같은 문자로 이루어진 경우
            System.out.println(-1);// 그런 부분 문자열이 없음
        } else {
            int len = str.length();// 문자열 전체 길이
            int half = len / 2;// 문자열 길이의 절반
            StringBuilder sb = new StringBuilder(str.substring(len - half));// 문자열 절반 복사
            if (sb.reverse().toString().equals(str.substring(0, half))) {// 팰린드롬인 경우
                System.out.println(len - 1);// 한글자 뺀 문자열 길이 출력
            } else {// 팰린드롬이 아닌 경우
                System.out.println(len);// 전체 문자열 길이 출력
            }
        }
    }
}
