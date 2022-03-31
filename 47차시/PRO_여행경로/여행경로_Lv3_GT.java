package com.ssafy.programas.DFS_BFS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 여행경로_Lv3_GT {
    public static void main(String[] args) {
        String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        String[] res = solution(tickets);
        for (String i : res) {
            System.out.print(i + " ");
        }


    }

    static boolean[] isVisited;
    static ArrayList<String> list;

    public static String[] solution(String[][] tickets) {
        String[] answer = new String[tickets.length + 1];
        list = new ArrayList<>();
        isVisited = new boolean[tickets.length];

        dfs(tickets, "ICN", "ICN", 0);
        Collections.sort(list);//알파벳 앞서는 순으로
        StringTokenizer st = new StringTokenizer(list.get(0), " ");
        for (int i = 0; i < tickets.length+1; i++) {
            answer[i] = st.nextToken();
        }
        return answer;
    }

    public static void dfs(String[][] tickets, String stack, String now, int cnt) {
        if (cnt == tickets.length) {
            list.add(stack);
            return;
        }
        for (int i = 0; i < tickets.length; i++) {
            if (isVisited[i]) //방문 체크
                continue;
            if (!tickets[i][0].equals(now)) //다음 공항으로 갈 수 있을지 체크
                continue;

            isVisited[i] = true;
            dfs(tickets, stack + " " + tickets[i][1], tickets[i][1], cnt + 1);
            isVisited[i] = false;
        }
    }
}
