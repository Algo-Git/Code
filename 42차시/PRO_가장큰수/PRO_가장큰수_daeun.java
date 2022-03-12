import java.util.Arrays;

public class PRO_가장큰수_daeun {
    public static void main(String[] args) {

//        System.out.print(Solution.solution(new int[]{6, 10, 2}));
        System.out.print(Solution.solution(new int[]{0, 0, 0}));
    }

    static class Solution {
        public static String solution(int[] numbers) {
            int n = numbers.length;

            // String 배열로 변환
            String[] s = new String[n];
            for (int i = 0; i < n; i++) {
                s[i] = Integer.toString(numbers[i]);
            }

            // s1+s2 의 문자열과 s2+s1의 문자열 중 큰 값으로 내림차순 정렬
            Arrays.sort(s, (o1, o2) -> {
                String s1 = o1 + o2;
                String s2 = o2 + o1;
                return -s1.compareTo(s2);
            });

            StringBuilder answer = new StringBuilder();
            for (int i = 0; i < n; i++) {
                answer.append(s[i]);
                if(s[0].equals("0")) break; // 첫번째가 0이면 답은 0이므로 break
            }

            return answer.toString();
        }
    }
}