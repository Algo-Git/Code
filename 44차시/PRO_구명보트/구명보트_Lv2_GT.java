package com.ssafy.programas.Greed;

import java.util.Arrays;

public class 구명보트_Lv2_GT {
    public static void main(String[] args) {
        int[] people = {70, 50, 80, 50};
        int limit = 100;
        System.out.println(solution(people, limit));
    }

    public static int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        //50 50 70 80
        //40 40 50 60
        int start = 0;

        for (int i = people.length - 1; i >= start; i--) {
            //무게가 최소 40이라 최대 2명까지 밖에 못탄다.
            if (people[i] + people[start] <= limit) {
                if(people[i]==-1||people[start]==-1)
                    continue;
                people[i] = -1;
                people[start] = -1;
                start++;
                answer++;

            }
        }
        for (int i = 0; i < people.length; i++) {
            if (people[i] != -1) {
                answer++;
            }
        }


        return answer;
    }

}
