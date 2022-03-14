package com.ssafy.programas.sort;

import java.util.Arrays;

/*
어떤 과학자가 발표한 논문 n편 중, h번 이상 인용된 논문이 h편 이상이고
나머지 논문이 h번 이하 인용되었다면 h의 최댓값이 이 과학자의 H-Index입니다.
 */
public class H_index_Lv2_GT {
    public static void main(String[] args) {
        int[] citations = {3, 0, 6, 1, 5};
//        Integer[] citations = {3, 0, 6, 1, 5}; //내림차순
//        Arrays.sort(citations, Collections.reverseOrder());
        System.out.println(solution(citations));
    }

    //6 5 3 1 0
    public static int solution(int[] citations) {
        Arrays.sort(citations);
        int cnt = 1;
        int answer = 0;
        for (int i = citations.length - 1; i >= 0; i--) {
            if (cnt <= citations[i]) {
                answer = cnt;
            }
            cnt++;
        }
        return answer;
    }

}
