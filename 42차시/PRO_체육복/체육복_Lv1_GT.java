package com.ssafy.programas.Greed;


import java.util.Arrays;

public class 체육복_Lv1_GT {
    public static void main(String[] args) {
        int n = 5;
        int[] lost = {2, 4};
        int[] reserve = {3};
        System.out.println(solution(n, lost, reserve));
    }

    //1 3 5  |  2 4
    public static int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length; //인터셉트 안 통한 사람 수(최소 인원)
        Arrays.sort(lost);
        Arrays.sort(reserve);

        //가져왔는데 인터셉트 당한 경우
        for (int i = 0; i < lost.length; i++) {
            for (int j = 0; j < reserve.length; j++) {
                if (lost[i] == reserve[j]) {
                    lost[i] = -1;
                    reserve[j] = -1;
                    answer++;
                }
            }
        }

        for (int i = 0; i < lost.length; i++) {
            for (int j = 0; j < reserve.length; j++) {
                if (reserve[j] == -1) //빌려주고 없음
                    continue;
                if (reserve[j] == lost[i] + 1 || reserve[j] == lost[i] - 1) {
                    reserve[j] = -1;
                    answer++;
                    break;
                }
            }
        }
        return answer;
    }
}
