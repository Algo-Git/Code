package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_흩날리는시험지속에서내평점이느껴진거야 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int K = Integer.parseInt(in[1]);

        Integer[] arr = new Integer[N];

        int low = 0, high = 0;

        in = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(in[i]);
            high += arr[i];
        }


        while (low <= high) {
            int mid = (low + high) / 2;
            int cnt = 1, sum = 0, min = Integer.MIN_VALUE;

            for (int i = 0; i < N; i++) {
                sum += arr[i];

                if (sum >= mid) {
                    cnt++;
                    min = Math.min(min, sum);
                    sum = 0;
                }
            }

            if (cnt > K) low = mid + 1;
            else high = mid - 1;
        }


        System.out.println(low - 1);

    }
}
