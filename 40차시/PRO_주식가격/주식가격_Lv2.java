package com.ssafy.programas.stackAndQueue;

public class 주식가격_Lv2 {
    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 2, 3};
        int[] res = new int[100000];
        res = solution(prices);
        System.out.println();
        for (int i = 0; i < prices.length; i++) {
            System.out.print(res[i] + " ");
        }

    }

    public static int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            int cnt = 0;
            for (int j = i+1; j < prices.length; j++) {
                if (prices[i] <= prices[j]) {
                    cnt++;
                } else {
                    cnt++;
                    break;
                }
            }
            answer[i] = cnt;
        }
        return answer;
    }
}
