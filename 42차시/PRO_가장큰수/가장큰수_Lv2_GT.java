package com.ssafy.programas.sort;

import java.util.Arrays;
import java.util.Comparator;

/*
0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요
 */
public class 가장큰수_Lv2_GT {
    public static void main(String[] args) {
        int[] numbers = {3, 30, 34, 5, 9};
        System.out.println(solution(numbers));
    }

    public static String solution(int[] numbers) {
        String[] strArr = new String[numbers.length];

        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = Integer.toString(numbers[i]);
        }
        Arrays.sort(strArr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1 + o2);
            }
        });
        //System.out.println(Arrays.toString(strArr));
        String answer = "";
        for (int i = 0; i < strArr.length; i++) {
            answer += strArr[i];
        }
        //주의! : "000" 이면 "0"이다.
        if (strArr[0].equals("0"))
            answer = "0";
        return answer;
    }

}
