package com.ssafy.programas.Greed;

import java.util.Arrays;

public class 단속카메라_Lv3_GT {
    public static void main(String[] args) {
        int[][] routes = {{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}};
        System.out.println(solution(routes));

    }

    public static int solution(int[][] routes) {
        int answer = 0;
        //끝나는 부분으로 오름차순
        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);
        int min = Integer.MIN_VALUE; //시작값의 끝부분
        for (int i = 0; i < routes.length; i++) {
            //끝 부분이 사직 시간보다 작은경우(이어지지 않은 경우)
            if (min < routes[i][0]) {
                min = routes[i][1]; //끝부분 교체
                answer++;
            }
        }
        return answer;
    }

}
