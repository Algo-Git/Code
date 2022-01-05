//[실버1] 수

import java.util.Scanner;

// 1. 가능한 숫자 만들기 => 최대 5자리 숫자. 9*9*8*7*6 = 27216 가지 경우
// 2. 에라토스테네스의 채로 소수 저장
// 3. 조건 1 만족하는 것들 저장
// 4. 조건 2 만족하는 것들 저장
public class BOJ_22943_yejinKim {
    static int K, M;
    static int[] nums;
    static boolean[] visited, notSosu, sosuSum, sosuMult;
    static int answer;
    static int Max;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        K = sc.nextInt();
        M = sc.nextInt();
        Max = (int) Math.pow(10, K);

        notSosu = new boolean[Max]; // true면 소수 아님 false면 소수임
        sosuSum = new boolean[Max]; // true면 서로 다른 소수 합임
        sosuMult = new boolean[Max]; // true면 소수 곱임

        nums = new int[K];
        visited = new boolean[10];
        answer = 0;
        eratos();
        makeSum();
        makeMult();


        perm(0, 0); // 순열
        System.out.println(answer);
    }

    // 에라토스테네스의 체

    private static void eratos() {
        notSosu[0] = true;
        notSosu[1] = true;
        for (int i = 2; i < Max; i++) {
            if (notSosu[i]) {
                continue;
            } else {
                for (int j = i + i; j < Max; j += i) {
                    notSosu[j] = true;
                }
            }
        }
    }

    // 서로 다른 두 소수 합
    private static void makeSum() {
        outer:
        for (int i = 2; i < Max; i++) {
            if (notSosu[i]) continue;

            for (int j = i + 1; j < Max; j++) {
                if (notSosu[j]) continue;
                if (i + j >= Max) continue outer;
                sosuSum[i + j] = true;
            }
        }
    }

    // 두 소수 곱
    private static void makeMult() {
        outer:
        for (int i = 2; i < Max; i++) {
            if (notSosu[i]) continue;

            for (int j = i; j < Max; j++) {
                if (notSosu[j]) continue;

                // 이부분 조심 .. long 타입으로 해야한다.
                // 예를 들어 K = 5 일 때 46349*46349=2148229801 => int이면 음수

                long num = (long) i * (long)j;
                if (num >= Max) continue outer;

                sosuMult[i * j] = true;
            }
        }
    }


    private static void perm(int cnt, int num) {
        if (cnt == K) {
            // 기저조건
            if (sosuSum[num]) {
                while (true) {
                    if (num % M == 0) { // 나누어떨어지면
                        num /= M;
                    } else {
                        break;
                    }
                }
                if (sosuMult[num]) {
                    answer++;
                }
            }
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (cnt == K - 1 && i == 0) continue; // 수의 맨 앞에는 0이 올 수 없다.

            if (visited[i]) continue;

            visited[i] = true;
            nums[cnt] = i;
            perm(cnt + 1, num + i * (int)Math.pow(10,cnt));
            visited[i] = false;
        }
    }
}
