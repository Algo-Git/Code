import java.util.Arrays;

public class PRO_42748_K번째수 {
    public static void main(String[] args) {

        System.out.print(Solution.solution(new int[]{1, 5, 2, 6, 3, 7, 4}, new int[][]{{2, 5, 3}, {4, 4, 1}, {1, 7, 3}}));
    }

    static class Solution {
        public static int[] solution(int[] array, int[][] commands) {
            int T = commands.length;
            int[] answer = new int[T];

            for (int t = 0; t < T; t++) {    // 테케수
                int[] tempArr = Arrays.copyOfRange(array, commands[t][0] - 1,
                        commands[t][1]);    // i번째 ~ j번째 까지 숫자 복사
                Arrays.sort(tempArr);   // 정렬
                answer[t] = tempArr[commands[t][2] - 1];    // 정렬후 k번째 숫자 구하기
            }

            return answer;
        }
    }
}