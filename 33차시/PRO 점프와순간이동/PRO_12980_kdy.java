import java.util.*;

public class Solution {

    /*
    건전지 사용량을 최소로 => 점프 최소로 하기
    2배씩 이동하고 홀수가 되는 순간만 1칸 점프
    */

    public int solution(int n) {
        int ans = 0;// 건전지 사용량의 최솟값
        while (n != 0) {// N ~ 0
            ans += n & 1;// 홀수면 1칸 점프
            n >>= 1;// 순간 이동
         }
        return ans;// 사용한 건전지 사용량 출력
    }
}
