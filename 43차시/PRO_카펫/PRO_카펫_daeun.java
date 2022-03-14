public class PRO_42842_카펫 {
    public static void main(String[] args) {

//        System.out.print(Solution.solution(10, 2));
//        System.out.print(Solution.solution(8, 1));
        System.out.print(Solution.solution(24, 24));
    }

    static class Solution {

        public static int[] solution(int brown, int yellow) {
            int[] answer = new int[2];

            int total = brown + yellow;

            double quo = 0;
            int div = 3;    // 가로 세로는 3 이상이어야 하므로 3부터 나눔
            while(true){
                quo = (double)total / div;  // brown 개수와 같은 몫, 나눌수를 찾아야하므로 double로
                // brown 개수는 (가로*2 + 세로*2 - 4)
                if(quo * 2 + (div << 1) - 4 == brown){ // brown 개수와 같은 몫(가로)와 나눈수(세로)일 때
                    answer[0] = (int)quo;
                    answer[1] = div;
                    break;
                }
                div++;
            }

            return answer;
        }
    }
}
