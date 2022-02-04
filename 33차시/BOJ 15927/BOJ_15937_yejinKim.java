import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// [골드5] 회문은 회문이 아니야
public class BOJ_25937_yejinKim {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        int size = s.length();

        // 1. 회문인지 아닌지 판단하기
        boolean isPalindrome = true;
        Stack<Character> stack = new Stack<>();

        if(size%2 == 1){ // 홀수라면 ?
            for(int i = 0; i<size/2; i++){
                stack.push(s.charAt(i));
            }

            // pop 하면서 확인하기
            for(int i = size/2+1; i<size; i++){
                if(stack.pop() == s.charAt(i)){
                    continue;
                }else{
                    isPalindrome = false;
                    break;
                }
            }

        }else{ // 짝수라면 ?
            for(int i = 0; i<size/2; i++){
                stack.push(s.charAt(i));
            }

            // pop 하면서 확인하기
            for(int i = size/2; i<size; i++){
                if(stack.pop() == s.charAt(i)){
                    continue;
                }else{
                    isPalindrome = false;
                    break;
                }
            }
        }

        // 만약 회문이 아니면? 총 길이가 답이다.
        if(!isPalindrome){
            System.out.println(size);
        }else{
            char first = s.charAt(0);
            boolean isAllSame = true;
            for(int i = 1; i<size; i++){
                if(s.charAt(i) != first){
                    isAllSame = false;
                    break;
                }
            }

            // 모든 글자가 같은 글자라면?
            if(isAllSame){
                System.out.println(-1);
            }else{
                System.out.println(size-1);
            }
        }
    }
}
