import java.util.*;

public class PRO_42840_모의고사 {
    public static void main(String[] args) {

        System.out.print(Solution.solution(new int[]{1, 2, 3, 4, 5}));
    }

    static class Solution {
        public static int[] solution(int[] answers) {
            int[] p1 = {1, 2, 3, 4, 5};
            int[] p2 = {2, 1, 2, 3, 2, 4, 2, 5};
            int[] p3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

            int idx1 = 0, idx2 = 0, idx3 = 0;
            int ans1 = 0, ans2 = 0, ans3 = 0;
            for (int i = 0; i < answers.length; i++) {
                if (p1[idx1++] == answers[i]) ans1++;   // 정답 개수 + 1
                if (p2[idx2++] == answers[i]) ans2++;
                if (p3[idx3++] == answers[i]) ans3++;
                if (idx1 == 5) idx1 = 0;    // 범위 벗어나면 인덱스 0으로 변경
                if (idx2 == 8) idx2 = 0;
                if (idx3 == 10) idx3 = 0;
            }

            int max = Math.max(Math.max(ans1, ans2), ans3); // 가장 많이 맞춘 개수 구하기

            ArrayList<Integer> list = new ArrayList<>();
            if (max == ans1) list.add(1);   // 1번 수포자가 가장 많이 맞췄을 때 리스트에 추가
            if (max == ans2) list.add(2);   // 2번 수포자가 가장 많이 맞췄을 때 리스트에 추가
            if (max == ans3) list.add(3);   // 3번 수포자가 가장 많이 맞췄을 때 리스트에 추가

            int[] answer = new int[list.size()];
            for (int i = 0; i < list.size(); i++) { // 리스트값을 배열에 넣기
                answer[i] = list.get(i);
            }

            return answer;
        }
    }
}