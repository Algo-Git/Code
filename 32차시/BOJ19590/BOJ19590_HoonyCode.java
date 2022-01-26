import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Math.*;

/**
 * 2022-01-26 by 김영훈
 * <p>
 * 문제이름 : 비드맨
 * 설명 : 구슬을 매칭 문제 -> 그리디
 */
public class BOJ19590 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int maxv = 0;
        long tot = 0;

        int in;
        for (int i = 0; i < N; i++) {
            in = Integer.parseInt(br.readLine());

            tot += in; // 구슬의 전체의 합
            maxv = max(maxv, in); // 가장 많은 구슬의 값
        }

        //1. 최댓값 > 나머지의 총합인 경우
        // 최대값이랑 부딪히면 된다. 따라서 답은 최댓값 - 나머지의 합
        if (tot - maxv > maxv) {
            System.out.println(tot & 1);
        } else {
            System.out.println(maxv - (tot - maxv));
        }
    }
}
