package com.ssafy.programas.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class 디스크컬트롤러_Lv3 {
    public static void main(String[] args) {
        int[][] jobs = {{0, 3}, {1, 9}, {2, 6}};
        System.out.println(solution(jobs));
    }

    public static int solution(int[][] jobs) {
        int answer = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);//처리 시간순
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);//시작시간 순서대로

        int nowTime= 0;//현재 시간
        int i = 0;

        //모두 수행할 떄 까지
        while (i < jobs.length || !pq.isEmpty()) {

            while (i < jobs.length && jobs[i][0] <= nowTime) {
                pq.offer(new int[]{jobs[i][0], jobs[i][1]});
                i++;
            }

            if(pq.isEmpty()) //비어있으면
                nowTime = jobs[i][0];//현재 시간을 가장 처음으로 맞춤
            else {
                int[] temp = pq.poll();
                answer += nowTime + temp[1] - temp[0]; //요청 ~ 종료시간
                nowTime += temp[1];
            }
        }
        return answer / jobs.length; //평균

    }
}
