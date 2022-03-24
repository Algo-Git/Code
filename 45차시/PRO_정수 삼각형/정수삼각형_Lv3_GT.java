package com.ssafy.programas.DP;

public class 정수삼각형_Lv3_GT {
    public static void main(String[] args) {
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        System.out.println(solution(triangle));
    }

    /*
    위->아래로 더해갈려니 시간초과가 나왔다

    아래 -> 위로 더해가면 시간 줜나 줄어든다!!!!!!!
     */
    public static int solution(int[][] triangle) {
        for (int i = triangle.length - 2; i >= 0; i--) {
            for (int j = 0; j < triangle[i].length; j++) { //가로 길이
                triangle[i][j] += Math.max(triangle[i + 1][j], triangle[i + 1][j + 1]);

            }
        }
        return triangle[0][0];
    }


//    static int answer = 0;
//
//    public static int solution(int[][] triangle) {
//        answer = 7;
//
//        dfs(triangle, 0, 0, 0);
//        return answer;
//    }
//
//    public static void dfs(int[][] triangle, int cnt, int sum, int location) {
//        if (cnt == triangle.length - 1) {
//            if (answer < sum + triangle[0][0])
//                answer = sum + triangle[0][0];
//            return;
//        }
//
//        dfs(triangle, cnt + 1, sum + triangle[cnt + 1][location], location);//왼쪽
//        dfs(triangle, cnt + 1, sum + triangle[cnt + 1][location + 1], location + 1); //오른쪽
//
//    }

}

