import java.util.*;

public class PRO_43238_입국심사 {
    public static void main(String[] args) {

        System.out.print(Solution.solution(
                6, new int[]{7, 10}
        ));

    }

    static class Solution {
        public static long solution(int n, int[] times) {

            Arrays.sort(times);

            long low = 1;
            long high = (long) times[times.length - 1] * n; // 최대 시간: times의 최댓값 * n명
            long mid;

            while (low < high) {
                mid = (low + high) / 2;

                // 가운데 값이, n명 모두 심사 완료 가능한 시간인지 체크
                long cnt = 0;
                for (int time : times) {
                    cnt += (mid / time);
                }

                if (cnt >= n) {   // n명을 모두 심사할 수 있으면 high 값 업데이트
                    high = mid;
                } else { // cnt < n
                    low = mid + 1;
                }
            }

            return high;    // 모두 심사 가능할 때 (cnt == n) high 값을 mid 값으로 변경했으므로 리턴
        }
    }
}