import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ22943 {

    static int K, M;
    static int answer;
    private static final int max = 100000;
    static boolean[] sum_sosu = new boolean[max]; // true 일때 소수
    static boolean[] mul_sosu = new boolean[max]; // true 일떄 소수
    static List<Integer> sosuList = new ArrayList<>();

    static int[] num;
    static boolean[] v;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        K = Integer.parseInt(in[0]);
        M = Integer.parseInt(in[1]);

        makeSosu();
        makeSumSosu();
        makeMulSosu();

        num = new int[K];
        v = new boolean[10];
        answer = 0;
        dfs(0);

        System.out.println(answer);
    }

    private static void makeMulSosu() {
        int len = sosuList.size();
        for (int i = 0; i < len; i++) {
            if (sosuList.get(i) * sosuList.get(i) >= max)
                break;
            for (int j = i; sosuList.get(i) * sosuList.get(j) < max; j++) {
                int mul = sosuList.get(i) * sosuList.get(j);
                mul_sosu[mul] = true;
            }
        }
    }

    private static void makeSumSosu() {
        int len = sosuList.size();

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len && sosuList.get(i) + sosuList.get(j) < max; j++) {
                int sum = sosuList.get(i) + sosuList.get(j);
                sum_sosu[sum] = true;
            }
        }
    }

    // 확인
    private static void makeSosu() {
        boolean[] sosu = new boolean[max]; //false 일떄 소수
        sosu[0] = true;
        sosu[1] = true;

        for (int i = 2; i < max; i++) {
            if (sosu[i]) continue;

            sosuList.add(i);

            for (int j = 2; i * j < max; j++) {
                sosu[i * j] = true;
            }
        }
    }

    private static void dfs(int dept) {

        if (dept == K) {

            int number = makeNum();

            //서로 다른 두개의 소수의 합으로 나타낼 수 있는 경우
            if (!sum_sosu[number]) return;

            //M으로 나누어 떨어지지 않을때까지 나눈 수가 두 개의 소수의 곱인 경우
            if (!mul_sosu[divideNum(number)]) return;

            answer++;
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (dept == 0 && i == 0) continue;
            if (v[i]) continue;
            v[i] = true;
            num[dept] = i;
            dfs(dept + 1);
            v[i] = false;
        }

    }

    private static int divideNum(int number) {

        while (number / M != 0) {
            number /= M;
        }
        return number;
    }

    private static int makeNum() {
        int number = num[0];

        for (int i = 1; i < K; i++) {
            number *= 10;
            number += num[i];
        }
        return number;
    }
}
