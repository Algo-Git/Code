package com.ssafy.programas.Greed;

public class 조이스틱_Lv2_GT {

    public static void main(String[] args) {
        String name = "JEROEN";
        System.out.println(solution(name));
    }

    public static int solution(String name) {
        int answer = 0;
        int len = name.length();
        int min = len - 1;

        for (int i = 0; i < len; i++) {
            char c = name.charAt(i);
            answer += Math.min(c - 'A', 'Z' - c + 1);//더 작은걸루 ,A->Z로 이동하니 +1


            int next = i + 1;
            //연속되는 A개수
            while (next < len && name.charAt(next) == 'A')
                next++;

            //앞,뒤 중 이동수수 적은 값
            min = Math.min(min, i + len - next + i);
            //BBBBAAAAAAAB
            min = Math.min(min, (len - next) * 2 + i);
        }
        answer += min;
        return answer;
    }
}
