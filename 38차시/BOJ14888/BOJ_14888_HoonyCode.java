import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class BOJ_14888_HoonyCode {
    static final int ADD = 0;
    static final int SUB = 1;
    static final int MUL = 2;
    static final int DIV = 3;
    static final int[] operator = new int[4];
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        StringTokenizer numbers = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i + 1] = Integer.parseInt(numbers.nextToken());
        StringTokenizer operators = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) operator[i] = Integer.parseInt(operators.nextToken());
        //입력 종료

        solution(new int[N - 1], 0);
        sb.append(max).append("\n").append(min);
        System.out.println(sb);
    }

    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    static void solution(int[] operators, int count) {
        if (count == operators.length) {
            int result = calculate(operators);
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operator[i] > 0) {
                operator[i]--;
                operators[count] = i;
                solution(operators, count + 1);
                operator[i]++;
            }
        }


    }

    static int calculate(int[] operator) {
        int result = arr[1];
        for (int i = 0; i < operator.length; i++) {
            switch (operator[i]) {
                case 0:
                    result += arr[i + 2];
                    break;
                case 1:
                    result -= arr[i + 2];
                    break;
                case 2:
                    result *= arr[i + 2];
                    break;
                case 3:
                    result /= arr[i + 2];
                    break;
            }
        }
        return result;
    }

}