import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2805_HoonyCode {

    static int N, M; // 상근이가 집으로 가져가려고 하는 나무의 길이 M이 주어진다.
    static int[] treeLen;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]); // 나무의 수 N
        M = Integer.parseInt(input[1]); // 집으로 가져가려고 하는 나무의 길이 M
        treeLen = new int[N];

        input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            treeLen[i] = Integer.parseInt(input[i]);
        }

        Arrays.sort(treeLen);

        int res = binarySearch();

        System.out.println(res);

    }

    static private int binarySearch() {
        int low = 0;
        int high = treeLen[N - 1];
        int mid = 0;

        while (low <= high) {

            mid = (low + high) >> 1;

            if (M <= sumTreeLen(mid))
                low = mid + 1;
            else
                high = mid - 1;

        }
        return high;
    }

    static private long sumTreeLen(int val) {

        long sum = 0;
        long temp = 0;
        for (int i = N - 1; i > -1; i--) {
            temp = treeLen[i] - val;
            if (temp < 0) break;
            sum += temp;
        }

        return sum;
    }
}
