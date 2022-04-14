package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_팰린드룸만들기_HoonyCode {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] chars = br.readLine().toCharArray();

        int len = chars.length;

        int answer = (len-1) * 2 + 1;

        if (len == 1){
            System.out.println(1);
            return;
        }


        // 홀수일 경우
        loop : for (int i  = len-2 ; i > len/2-1 ; i--){

            for (int j = 1; j + i < len;  j++){
                if (chars[i-j] != chars[i+j]) continue loop;
            }

            answer = Math.min(answer, i * 2 + 1 );
        }


        // 짝수일 경우
        int end = len;
        if (len % 2 == 1) end++;
        loop : for (int i  = len-2 ; i >= end/2-1 ; i--){

            for (int j = 1; j + i < len;  j++){
                if (chars[i-j+1] != chars[i+j]) continue loop;
            }

            answer = Math.min(answer, (i+1)*2) ;
        }

        System.out.println(answer);
    }
}
