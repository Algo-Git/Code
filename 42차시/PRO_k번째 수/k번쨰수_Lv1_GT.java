package com.ssafy.programas.sort;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
array의 i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때, k번째에 있는 수를 구하려 합니다.
 */
public class k번쨰수_Lv1_GT {
    public static void main(String[] args) {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};

        int[] res = solution(array, commands);

        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }

    public static int[] solution(int[] array, int[][] commands) {
        Queue<Integer> q = new LinkedList<>();
        int[] answer = new int[commands.length];
        for (int i = 0; i < commands.length; i++) {
            int[] temp = Arrays.copyOfRange(array, commands[i][0] - 1, commands[i][1]);//from이상 to미만
            Arrays.sort(temp);
            q.offer(temp[commands[i][2] - 1]);
        }

        for (int i = 0; i < commands.length; i++) {
            answer[i] = q.poll();
        }

        return answer;
    }
}
