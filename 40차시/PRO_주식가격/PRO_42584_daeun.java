public class PRO_42584_주식가격 {
    public static void main(String[] args) {

        System.out.print(Solution.solution(new int[]{1, 2, 3, 2, 3}));
    }

    static class Solution {
        public static int[] solution(int[] prices) {
            int n = prices.length;
            int[] answer = new int[n];

            // i 번째 가격보다 낮은 가격이 있기 전까지 몇개인지 카운트, answer 배열에 넣기,,
            for (int i = 0; i < n; i++){
                int cnt = 0;
                for (int j = i + 1; j < n; j++){
                    cnt++;
                    if(prices[i] > prices[j]) break;
                }
                answer[i] = cnt;
            }

            return answer;
        }
    }
}