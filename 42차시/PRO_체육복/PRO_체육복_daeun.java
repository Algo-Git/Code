import java.util.Arrays;

public class PRO_42862_체육복 {
    public static void main(String[] args) {

//        System.out.print(Solution.solution(5, new int[]{2, 4}, new int[]{1, 3, 5}));
//        System.out.print(Solution.solution(5, new int[]{2, 4}, new int[]{3}));
//        System.out.print(Solution.solution(3, new int[]{1, 2}, new int[]{2, 3}));
        System.out.print(Solution.solution(5, new int[]{4, 2}, new int[]{3, 5}));
    }

    static class Solution {
        public static int solution(int n, int[] lost, int[] reserve) {
            int answer = n - lost.length;   // 도난 안당한 학생 수로 초기화

            boolean[] lostArr = new boolean[n + 1];
            boolean[] reserveArr = new boolean[n + 1];

            // 도난당한 학생 체크
            for (int i = 0; i < lost.length; i++){
                lostArr[lost[i]] = true;
            }
            // 여벌을 가진 학생 체크
            for (int i = 0; i < reserve.length; i++){
                reserveArr[reserve[i]] = true;
            }

            // lost, reserve 오름차순 정렬 : 앞뒤 순서대로 여벌을 주기 위해
            Arrays.sort(lost);
            Arrays.sort(reserve);

            // 도난당한 학생
            for(int i : lost){
                // 도난당했고 여벌을 가진 학생은 자신에게 체육복 여벌 주기
                if(lostArr[i] && reserveArr[i]){
                    answer++;
                    lostArr[i] = false; // 자신에게 여벌 줬으므로 도난 X, 여벌 X 로 변경
                    reserveArr[i] = false;
                }
                // 자신의 왼쪽 학생이 여벌이 있을 때, 도난당했고 여벌을 가진 학생이 아닐때 여벌 주기
                else if(i - 1 > 0 && reserveArr[i - 1]){
                    if(lostArr[i - 1] && reserveArr[i - 1]) continue;
                    answer++;
                    reserveArr[i - 1] = false;  // 여벌 줬으므로 여벌이 없다고 체크
                }
                // 자신의 오른쪽 학생이 여벌이 있을 때, 도난당했고 여벌을 가진 학생이 아닐때 여벌 주기
                else if(i + 1 <= n && reserveArr[i + 1]){
                    if(lostArr[i + 1] && reserveArr[i + 1]) continue;
                    answer++;
                    reserveArr[i + 1] = false;  // 여벌 줬으므로 여벌이 없다고 체크
                }
            }

            return answer;
        }
    }
}