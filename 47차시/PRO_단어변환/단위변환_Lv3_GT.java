package com.ssafy.programas.DFS_BFS;

public class 단위변환_Lv3_GT {
    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};

        System.out.println(solution(begin, target, words));

    }

    static boolean[] isVisited;
    static int min = 999;

    public static int solution(String begin, String target, String[] words) {
        isVisited = new boolean[words.length];

        dfs(words, begin, target, 0);

        if (min == 999)
            min = 0;
        return min;
    }

    public static void dfs(String[] words, String begin, String target, int cnt) {
        if (cnt >= min) //최솟값 가지치기
            return;

        if (begin.equals(target)) {
            min = cnt;
            return;
        }
        for (int i = 0; i < words.length; i++) {
            if (isVisited[i])
                continue;
            if (!isChecked(words[i], begin))
                continue;

            isVisited[i] = true;
            dfs(words, words[i], target, cnt + 1);
            isVisited[i] = false;


        }
    }

    public static boolean isChecked(String str1, String str2) {
        boolean res;
        int check = 0;
        for (int i = 0; i < str1.length(); i++) {
            char s1 = str1.charAt(i);
            char s2 = str2.charAt(i);
            if (s1 != s2) //다른 개수 체크
                check++;
        }
        res = check == 1; //

        return res;
    }
}

