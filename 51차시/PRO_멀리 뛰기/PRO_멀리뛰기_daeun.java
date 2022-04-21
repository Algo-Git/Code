public class PRO_12914_멀리뛰기 {
    public static void main(String[] args) {
        System.out.print(solution(4));
    }

    public static long solution(int n) {
        long answer = 0;

        long first = 1, second = 2;
        if (n == 1) answer = 1;
        else if(n == 2) answer = 2;
        else {
            for(int i = 2; i < n; i++){
                answer = (first + second) % 1234567;
                first = second;
                second = answer;
            }
        }

        return answer;
    }
}