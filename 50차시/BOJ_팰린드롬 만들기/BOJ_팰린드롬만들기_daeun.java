import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1254_팰린드롬만들기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        int n = s.length(); // 문자열 길이
        int half = n / 2;   // 문자열의 절반 길이

        for (int i = 0; i < half; i++) {
            if (s.charAt(i) != s.charAt(n - 1 - i)) { // 팰린드롬이 아니라면
                int idx = n - 1;    // 팰린드롬인 것까지의 인덱스 저장 변수
                for (int j = n - 2; j >= 0; j--) {
                    String temp = s.substring(j, n);
                    // substring이 팰린드롬인지 확인
                    boolean check = false;
                    for(int k = 0; k < temp.length(); k++){
                        // substring이 팰린드롬이 아니라면
                        if(temp.charAt(k) != temp.charAt(temp.length() - 1 - k)) {
                            check = true;
                            break;
                        }
                    }
                    if(!check){ // substring이 팰린드롬이라면 idx (팰린드롬까지의 인덱스) 변경
                        idx = j;
                    }
                }
                n += idx;   // 최종 문자열 길이 = 원래 문자열 길이 + 팰린드롬이 아닌것 까지의 길이
                break;
            }
        }

        System.out.print(n);
    }
}