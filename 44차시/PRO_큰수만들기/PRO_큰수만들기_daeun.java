
public class PRO_42883_큰수만들기 {
    public static void main(String[] args) {

//        System.out.print(Solution.solution("1924", 2));
//        System.out.print(Solution.solution("1231234", 3));
        System.out.print(Solution.solution("4177252841", 4));
    }

    static class Solution {

        public static String solution(String number, int k) {
            StringBuilder answer = new StringBuilder();

            int n = number.length();
            int total = number.length() - k;    // 만들어야 하는 숫자 개수
            int start = 0;
            while(total > 0){
                int end = n - (total - 1);  // 구할 숫자 개수 - 1 이전까지 중에 1개를 구한다.
                int max = number.charAt(start);
                int idx = start;
                for(int i = start + 1; i < end; i++){
                    if(max < number.charAt(i)){ // 현재 범위에서 가장 큰 수 구하기
                        max = number.charAt(i);
                        idx = i;
                    }
                }
                answer.append(number.charAt(idx));
                start = idx + 1;    // 구한 숫자의 다음 인덱스부터 시작해서 남은 숫자 구하기
                total--;    // 1개를 구했으므로 총 구할 숫자 개수에서 - 1
            }

            return answer.toString();
        }
    }
}