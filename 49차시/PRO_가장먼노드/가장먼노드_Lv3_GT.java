package com.ssafy.programas.Graph;

import java.util.LinkedList;
import java.util.Queue;

public class 가장먼노드_Lv3_GT {
    public static void main(String[] args) {
        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        int n = 6;
        System.out.println(solution(n, edge));
    }


    public static int solution(int n, int[][] edge) {
        int answer = 0;
        boolean[] isVisited = new boolean[n];
        boolean[][] map = new boolean[n][n];
        Queue<Integer> q = new LinkedList<>();

        for (int[] k : edge) {
            map[k[0] - 1][k[1] - 1] = true;
            map[k[1] - 1][k[0] - 1] = true;
        }
        isVisited[0] = true;
        q.add(0);
        while (!q.isEmpty()) {
            int size = q.size();
            //큐에 있는 개수 만큼 돌고
            for (int i = 0; i < size; i++) {
                int temp = q.poll();
                //노드 개수 만큼 돈다
                for (int j = 0; j < n; j++) {
                    //지나 갈 수 있는지와 방문 체크
                    if(map[j][temp]&&!isVisited[j]){
                        isVisited[j]=true;
                        q.add(j);
                    }
                }
            }
            //가장 마지막에 남아 있는게 가장 마지막 큐의 사이즈
            answer = size;
        }
        return answer;
    }
}
