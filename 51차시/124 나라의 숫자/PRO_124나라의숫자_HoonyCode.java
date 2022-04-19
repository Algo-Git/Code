package PRO;

public class PRO_124나라의숫자_HoonyCode {

    public static void main(String[] args) {
        System.out.println(Solution.solution(14));

    }

    static class Solution {
        public static String solution(int n) {
            StringBuilder sb = new StringBuilder();

            // 1:1 2:2 3:4 4:11
            int temp = n;
            int remain;

            while (temp > 0) {
                remain = temp % 3;
                if (remain == 0){
                    temp = temp/3 - 1;
                }else
                    temp = temp/3;

                if (remain == 0) {
                    sb.append(4);
                } else {
                    sb.append(remain);
                }
            }

            return sb.reverse().toString();
        }
    }
}
