package com.ssafy.programas.DFS_BFS;

public class 네트워크_Lv3_GT {
    public static void main(String[] args) {
        int n = 3;
        int[][] computers = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        System.out.println(solution(n, computers));
    }
    static boolean[] isVisited;
    public static int solution(int n, int[][] computers) {
        int answer = 0;
        isVisited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!isVisited[i]) {
                answer++;
                dfs(i, computers);
            }
        }
        return answer;
    }

    public static void dfs(int point, int[][] computers) {
        isVisited[point] = true;
        for (int i = 0; i < computers.length; i++) {
            if(isVisited[i])//방문 체크
                continue;
            if(computers[point][i]==0) //갈수 없으면 패스
                continue;
            dfs(i, computers);
        }
    }
}
