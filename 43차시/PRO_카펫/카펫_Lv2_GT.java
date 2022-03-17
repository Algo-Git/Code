package com.ssafy.programas.brute_force;

public class 카펫_Lv2_GT {
    public static void main(String[] args) {
        int brown = 10;
        int yellow = 2;
        int[] res = solution(brown, yellow);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }

    public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];//가로 세로
        int sum = brown + yellow;

        for (int i = 3; i <= sum; i++) {
            int x = i;  //가로
            int y = sum / x; //세로
            if(sum%x!=0)
                continue;
            int c = (x - 2) * (y - 2);
            if(c==yellow){
                answer[0]=y;//행
                answer[1]=x;//열
                return answer;
            }


        }

        return answer;
    }
}
