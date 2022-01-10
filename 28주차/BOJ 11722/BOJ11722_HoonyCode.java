import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ11722 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] count = new int[N];
        int[] arr = new int[N];

        String[] in = br.readLine().split(" ");
        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(in[i]);
        }

        for (int i = 0 ; i < N-1 ; i++){
            for (int j = i+1 ; j < N ; j++){
                if (arr[i] > arr[j]) count[j] = Math.max(count[j],count[i] + 1);
            }
        }

        Arrays.sort(count);
        System.out.println(count[N-1] + 1);

    }
}
