import java.util.Arrays;

public class PRO_42747_HIndex {
    public static void main(String[] args) {

        System.out.print(Solution.solution(new int[]{3, 0, 6, 1, 5}));
    }

    static class Solution {
        public static int solution(int[] citations) {
            int answer = 0;
            int n = citations.length;

            Arrays.sort(citations);
            int idx = 0;
            for (int h = 1; h <= n; h++){
                for (;idx < n; idx++){  // h 미만의 인덱스는 볼 필요 없음
                    if(citations[idx] >= h){  // h번 이상 인용된 논문
                        if(n - idx >= h && idx <= h){   // h편 이상, 나머지 논문이 h번 이하
                            answer = Math.max(answer, h);   // h 큰 값으로 업데이트
                        }
                        break;  // h번 이상 인용된 논문을 찾았으므로 break
                    }
                }
            }

            return answer;
        }
    }
}