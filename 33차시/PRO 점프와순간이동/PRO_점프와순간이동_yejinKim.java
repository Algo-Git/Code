import java.util.*;

public class Solution {
    public int solution(int n) {
        int num = n;
        int answer = 0;
        while(num != 0){
            if(num%2 == 1){
                num/=2;
                answer ++;
            }else{
                num/=2;
            }
        }
        return answer;
    }
}
