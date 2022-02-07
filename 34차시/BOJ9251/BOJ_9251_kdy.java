import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N9251 {// BOJ 9251. LCS

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();// 문자열1
        String str2 = br.readLine();// 문자열2
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];// 최장 공통 부분 문자열 길이

        for (int i = 1; i < str1.length() + 1; i++) {
            for (int j = 1; j < str2.length() + 1; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1))// 두 문자가 같은 경우
                    dp[i][j] = dp[i - 1][j - 1] + 1;// 이전 글자까지 길이 + 1
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);// 두 문자가 다른 경우 이전 문자열까지 길이 중 긴 길이
            }
        }
        System.out.println(dp[str1.length()][str2.length()]);// 최장 공통 부분 문자열 길이 출력
    }
}
