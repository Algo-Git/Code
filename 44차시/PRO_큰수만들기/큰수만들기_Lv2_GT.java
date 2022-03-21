package com.ssafy.programas.Greed;

public class 큰수만들기_Lv2_GT {
    public static void main(String[] args) {
        String res = solution("4177252841", 4);
        System.out.println(res);
    }

    public static String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int index = 0; // 뽑은 숫자 위치 바로 뒤
        for (int i = 0; i < number.length() - k; i++) { //for문 한번당 숫자 하나 뽑음
            int max = 0;
            //(number.length() - k)자리를 return 해야 되므로 뒤에서 (number.length() - k)번째에 위치한
            //값들을 탐색한다-> 6자리를 return 해야되서 시작은 뒤에서 6번째 위치한 '41772'를 탐색
            for (int j = index; j <= k + i; j++) {
                if (max < number.charAt(j)-'0') {
                    max = number.charAt(j)-'0';
                    index = j + 1;//뽑은 숫자의 앞은 볼 필요가 없다.
                }
            }
            sb.append(max);
        }

        return sb.toString();
    }


//    static int[] arr, temp;
//    static int max, N, len;
//
//    public static String solution(String number, int k) {
//        len = number.length();
//        arr = new int[len];
//        N = len - k; //결과 값의 크기
//        temp = new int[N]; //결과를 담을 배열
//        for (int i = 0; i < len; i++) {
//            arr[i] = number.charAt(i) - '0';
//        }
//
//        comb(0,1);  // nPr 조합
//        return Integer.toString(max);
//    }
//
//    public static void comb(int cnt, int start) {
//        if (cnt == N) {
//            String str = "";
//            for (int i = 0; i < N; i++) {
//                str += Integer.toString(temp[i]); // 배열을 String으로
//            }
//            int t = Integer.parseInt(str); //숫자 비교를 위해 String형 변환
//            if (max < t)
//                max = t;
//            return;
//        }
//        for (int i = start; i < len; i++) {
//            temp[cnt] = arr[i];
//            comb(cnt + 1, i + 1);
//        }
//    }
}
